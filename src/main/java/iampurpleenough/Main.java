package iampurpleenough;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

public class Main {
	private static final URI BASE = URI.create("http://iampurpleenough.cloud-nexus.se:4434/");
	private static final URI CHALLENGE = BASE.resolve("/challenge");
	private static final URI SUFFIX = BASE.resolve("/suffix");

	private static JsonReader newJsonReader(URL url) throws IOException {
		return Json.createReader(url.openStream());
	}

	private static Function<JsonObject, Double> getDoubleField(String name) {
		return o -> o.getJsonNumber(name).doubleValue();
	}

	private static Function<JsonObject, String> getStringField(String name) {
		return o -> o.getString(name);
	}

	private static List<JsonObject> readObjectArrayFrom(JsonReader jsonReader) {
		return jsonReader.readArray().getValuesAs(JsonObject.class);
	}

	public static void main(String[] args) throws IOException {
		try (JsonReader challengeReader = newJsonReader(CHALLENGE.toURL());
			 JsonReader suffixReader = newJsonReader(SUFFIX.toURL());
			 JsonReader tableReader = newJsonReader(Main.class.getResource("/translation.json"))) {

			Map<Double, String> translation = readObjectArrayFrom(tableReader).stream()
				.collect(toMap(
					getDoubleField("value"),
					getStringField("letter")));

			String challengeText = readObjectArrayFrom(challengeReader).stream()
				.map(getDoubleField("value"))
				.map(translation::get)
				.collect(joining());

			String suffix = suffixReader.readObject().getString("emailSuffix");

			System.err.println(challengeText + suffix);
		}
	}
}
