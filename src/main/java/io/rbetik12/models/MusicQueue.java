package io.rbetik12.models;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class MusicQueue implements MusicCollection, Serializable {
    private PriorityQueue<MusicBand> queue;

    @Override
    public List<MusicBand> toList() {
        return new ArrayList<>(queue);
    }

    public MusicQueue() {
        queue = new PriorityQueue<>();
    }

    @Override
    public void add(MusicBand e, User user) {
        e.setCreationDate(ZonedDateTime.now());
        e.setAuthor(user);
        updateQueue();
    }

    @Override
    public void update(int id, MusicBand e) {

    }

    @Override
    public void remove(int id, int userId) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void addIfMin(MusicBand e) {

    }

    @Override
    public void removeGreater(MusicBand e, int userId) {

    }

    @Override
    public void removeLower(MusicBand e, int userId) {

    }

    @Override
    public void minByCreationDate() {

    }

    @Override
    public void filterByNumberOfParticipants(int number) {

    }

    @Override
    public MusicBand get(int index) {
        int i = 0;
        for (MusicBand e: queue) {
            if (i == index) return e;
            i += 1;
        }
        return null;
    }

    public void updateQueue() {
    }
}
