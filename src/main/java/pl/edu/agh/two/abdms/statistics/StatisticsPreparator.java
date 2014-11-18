package pl.edu.agh.two.abdms.statistics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.math3.stat.descriptive.moment.Skewness;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import pl.edu.agh.two.abdms.data.loader.DataModel;

public class StatisticsPreparator {
	
	private DataModel dataModel;
	private Map<String, ColumnType> columnTypeMap;
	
	private Map<String, Double> averages;
	private Map<String, Double> medians;
	private Map<String, Double> skewnesses;
	private Map<String, Integer> uniqueValueCounts;
	
	public StatisticsPreparator(DataModel dataModel) {
		this.dataModel = dataModel;
		mapColumns();
		createStatistics();
	}
	
	public Map<String, Double> getAverages() {
		return averages;
	}
	
	public Map<String, Double> getMedians() {
		return medians;
	}
	
	public Map<String, Double> getSkewnesses() {
		return skewnesses;
	}
	
	public Map<String, Integer> getUniqueValueCounts() {
		return uniqueValueCounts;
	}
	
	public Double getAverage(String column) {
		if (averages.containsKey(column)) {
			return averages.get(column);
		} else {
			return null;
		}
	}
	
	public Double getMedian(String column) {
		if (medians.containsKey(column)) {
			return medians.get(column);
		} else {
			return null;
		}
	}
	
	public Double getSkewness(String column) {
		if (skewnesses.containsKey(column)) {
			return skewnesses.get(column);
		} else {
			return null;
		}
	}
	
	public Integer getUniqueValueCount(String column) {
		if (uniqueValueCounts.containsKey(column)) {
			return uniqueValueCounts.get(column);
		} else {
			return null;
		}
	}
	
	private void mapColumns() {
		columnTypeMap = new HashMap<String, ColumnType>();
		for (String column : dataModel.getColumnValues()) {
			int rowId = 0;
			boolean found = false;
			while (!found) {
				try {
					String value = dataModel.getValue(rowId, column);
					ColumnType type = parseValue(value);
					columnTypeMap.put(column, type);
					found = true;
				} catch (NoSuchElementException e) {
					++rowId;
				} catch (ArrayIndexOutOfBoundsException e) {
					found = true;
				}
			}
		}
	}
	
	private void createStatistics() {
		averages = new HashMap<String, Double>();
		medians = new HashMap<String, Double>();
		skewnesses = new HashMap<String, Double>();
		uniqueValueCounts = new HashMap<String, Integer>();
		for (String column : columnTypeMap.keySet()) {
			if (columnTypeMap.get(column) == ColumnType.NUMBER) {
				createStatisticsForNumberColumn(column);
			} else {
				createStatisticsForStringColumn(column);
			}
		}
	}
	
	private void createStatisticsForNumberColumn(String column) {
		try {
			
			String[] columnValues = dataModel.getValuesForColumn(column);
			int count = columnValues.length;
			double[] values = new double[count];
			int counter = 0;
			for (String columnValue : columnValues) {
				values[counter] = Double.parseDouble(columnValue);
				++counter;
			}
			
			// average
			double average = 0;
			for (double value : values) {
				average += (value / count);
			}
			averages.put(column, average);
			
			// median
			Median medianEvaluator = new Median();
			medianEvaluator.setData(values);
			double median = medianEvaluator.evaluate();
			medians.put(column, median);
			
			// skewness
			Skewness skewnessEvaluator = new Skewness();
			skewnessEvaluator.setData(values);
			double skewness = skewnessEvaluator.evaluate();
			skewnesses.put(column, skewness);
			
		} catch (Exception e) {
			createStatisticsForStringColumn(column);
		}
	}
	
	private void createStatisticsForStringColumn(String column) {
		// number of unique values
		String[] columnValues = dataModel.getColumnValues();
		Set<String> valueSet = new HashSet<String>();
		for (String columnValue : columnValues) {
			valueSet.add(columnValue);
		}
		uniqueValueCounts.put(column, valueSet.size());
	}
	
	private ColumnType parseValue(String value) {
		try {
			Double.parseDouble(value);
			return ColumnType.NUMBER;
		} catch (Exception e) {
			return ColumnType.STRING;
		}
	}
	
}
