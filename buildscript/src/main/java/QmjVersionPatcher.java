import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.github.coolcrabs.brachyura.processing.ProcessingEntry;
import io.github.coolcrabs.brachyura.processing.ProcessingSink;
import io.github.coolcrabs.brachyura.processing.Processor;
import io.github.coolcrabs.brachyura.util.GsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class QmjVersionPatcher implements Processor {
    @Override
    public void process(Collection<ProcessingEntry> inputs, ProcessingSink sink) throws IOException {
        for (ProcessingEntry e : inputs) {
            if ("quilt.mod.json".equals(e.id.path)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();
                JsonObject quiltModJson;
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(e.in.get(), StandardCharsets.UTF_8))) {
                    quiltModJson = gson.fromJson(reader, JsonObject.class);
                }
                quiltModJson.getAsJsonObject("quilt_loader").addProperty("version", Versions.MOD_VERSION);
                sink.sink(() -> GsonUtil.toIs(quiltModJson, gson), e.id);
            } else {
                sink.sink(e.in, e.id);
            }
        }
    }
}