package videos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VideoPlatform {
    private List<Channel> channels = new ArrayList<>();

    public List<Channel> getChannels() {
        return channels;
    }

    public void readDataFromFile(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                addChannel(line);
            }
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot open file for read!", ioe);
        }
    }

    public int calculateSumOfVideos() {
        int sum = 0;
        for (Channel chn : channels) {
            sum += chn.getNumber_of_videos();
        }
        return sum;
    }

    private void addChannel(String line) {
        String[] elements = line.split(";");
        channels.add(new Channel(elements[0], Integer.parseInt(elements[1]), Integer.parseInt(elements[2])));
    }
}
