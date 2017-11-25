package ru.kononov.todo.api.codecs;

import java.time.LocalDateTime;

import javax.faces.flow.builder.ReturnBuilder;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/**
 * преобразование Bson <-> LocalDateTime
 * 
 * @author admin
 *
 */
public class LocalDateTimeCodec implements Codec<LocalDateTime> {

	/**
	 * LocalDateTime -> Bson
	 */
    @Override
    public void encode(BsonWriter writer, LocalDateTime t, EncoderContext ec) {
        writer.writeString(t.toString());
    }

    @Override
    public Class<LocalDateTime> getEncoderClass() {
        return LocalDateTime.class;
    }

    /**
     * Bson -> LocalDateTime
     * @return LocalDateTime
     */
    @Override
    public LocalDateTime decode(BsonReader reader, DecoderContext dc) {
        String date = reader.readString();
        return LocalDateTime.parse(date);
    }
}

