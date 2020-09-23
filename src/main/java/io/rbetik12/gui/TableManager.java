package io.rbetik12.gui;

import io.rbetik12.models.Coordinates;
import io.rbetik12.models.Label;
import io.rbetik12.models.MusicBand;
import io.rbetik12.models.MusicGenre;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class TableManager {

    private static List<MusicBand> fetchTable() {
        List<MusicBand> musicBands = new ArrayList<> ();
        musicBands.add(new MusicBand("Band", new Coordinates(10, 10), 10, MusicGenre.HIP_HOP, new Label("kek")));
        musicBands.add(new MusicBand("Band1", new Coordinates(10, 10), 10, MusicGenre.HIP_HOP, new Label("lmao")));
        musicBands.get(0).setCreationDate(ZonedDateTime.now());
        musicBands.get(1).setCreationDate(ZonedDateTime.now());
        return musicBands;
    }

    public static String[][] getTable() {
        List<MusicBand> bands = fetchTable();
        String[][] bandsTable = new String[bands.size()][6];

        for (int i = 0; i < bands.size(); i++) {
            MusicBand band = bands.get(i);

            bandsTable[i][0] = String.valueOf(band.getId());
            bandsTable[i][1] = band.getName();
            bandsTable[i][2] = band.getCreationDate().toString();
            bandsTable[i][3] = band.getNumberOfParticipants().toString();
            bandsTable[i][4] = band.getGenre().toString();
            bandsTable[i][5] = band.getLabel().getName();
        }

        return bandsTable;
    }
}
