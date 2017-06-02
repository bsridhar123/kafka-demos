package com.demo.serialization;

import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.demo.model.Employee;

public class EmployeeSerializer implements Serializer<Employee> {
	private String encoding = "UTF8";

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// nothing to configure
	}

	@Override
	public byte[] serialize(String topic, Employee data) {

		int sizeOfName;
		int sizeOfDate;
		byte[] serializedName;
		byte[] serializedDate;

		try {
			if (data == null)
				return null;
			serializedName = data.getEmpName().getBytes(encoding);
			sizeOfName = serializedName.length;
			serializedDate = data.getEmpStartDate().toString().getBytes(encoding);
			sizeOfDate = serializedDate.length;

			ByteBuffer buf = ByteBuffer.allocate(4 + 4 + sizeOfName + 4 + sizeOfDate);
			buf.putInt(data.getEmpId());
			buf.putInt(sizeOfName);
			buf.put(serializedName);
			buf.putInt(sizeOfDate);
			buf.put(serializedDate);

			return buf.array();

		} catch (Exception e) {
			throw new SerializationException("Error when serializing Supplier to byte[]");
		}
	}

	@Override
	public void close() {
		// nothing to do
	}
}