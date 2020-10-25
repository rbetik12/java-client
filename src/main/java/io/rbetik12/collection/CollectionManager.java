package io.rbetik12.collection;

import io.rbetik12.models.MusicCollection;
import io.rbetik12.models.MusicQueue;

public class CollectionManager {
    private static CollectionManager instance;
    private MusicCollection collection;

    private CollectionManager() {
        collection = new MusicQueue();
    }

    public MusicCollection getCollection() {
        return collection;
    }

    public void setCollection(MusicCollection collection) {
        this.collection = collection;
    }

    public static CollectionManager getManager() {
        if (instance == null)
            instance = new CollectionManager();
        return instance;
    }
}
