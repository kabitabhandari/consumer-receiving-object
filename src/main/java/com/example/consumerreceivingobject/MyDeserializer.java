package com.example.consumerreceivingobject;

import lombok.SneakyThrows;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * converts Byte array to Object
 */
public class MyDeserializer implements Deserializer<MessageSupplier> {
    private final String encoding = "UTF8";

    @Override
    public void configure(Map configs, boolean isKey) {
        //nothing to configure

    }

    @SneakyThrows
    @Override
    public MessageSupplier deserialize(String topic, byte[] data) { //data [0,0,12,110....] ---> which holds-->  (id, name, desc, date)
        try {
            if (data == null) {
                System.out.println("Null recieved at deserialize");
                return null;
            }
            System.out.println("data " + data);
            ByteBuffer byteBuffer = ByteBuffer.wrap(data);
            int id = byteBuffer.getInt();

            int sizeOfName = byteBuffer.getInt();
            byte[] nameBytes = new byte[sizeOfName];
            byteBuffer.get(nameBytes);
            String deserializedName = new String(nameBytes, encoding);

            int sizeOfDescription = byteBuffer.getInt();
            byte[] descBytes = new byte[sizeOfDescription];
            byteBuffer.get(descBytes);
            String deserializedDescription = new String(descBytes, encoding);


            return new MessageSupplier(id, deserializedName, deserializedDescription, new Date());


        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Supplier");
        }


    }

    @Override
    public void close() {
        //nothing to configure

    }
}
