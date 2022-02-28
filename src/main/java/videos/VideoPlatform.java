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
            String[] elements;
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                elements = line.split(";");
                String name = elements[0];
                int subs = Integer.parseInt(elements[1]);
                int nrv = Integer.parseInt(elements[2]);
                channels.add(new Channel(name, subs, nrv));
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
}
