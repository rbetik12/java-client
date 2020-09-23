package io.rbetik12.gui;

import io.rbetik12.models.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TableManager {

    private static List<MusicBand> fetchTable() {
        List<MusicBand> musicBands = new ArrayList<> ();
        musicBands.add(new MusicBand("Band", new Coordinates(10, 10), 10, MusicGenre.HIP_HOP, new Label("a")));
        musicBands.add(new MusicBand("Band1", new Coordinates(10, 10), 30, MusicGenre.HIP_HOP, new Label("b")));
        musicBands.add(new MusicBand("Band1", new Coordinates(10, 10), 20, MusicGenre.HIP_HOP, new Label("c")));

        musicBands.get(0).setCreationDate(ZonedDateTime.now());
        musicBands.get(1).setCreationDate(ZonedDateTime.now());
        musicBands.get(2).setCreationDate(ZonedDateTime.now());
        return musicBands;
    }

    private static List<MusicBand> sortList(SortBy sort, List<MusicBand> bands) {
        switch (sort) {
            case id:
                bands = bands.stream().sorted(Comparator.comparing(MusicBand::getId)).collect(Collectors.toList());
                break;
            case name:
                bands = bands.stream().sorted(Comparator.comparing(MusicBand::getName)).collect(Collectors.toList());
                break;
            case creationDate:
                bands = bands.stream().sorted(Comparator.comparing(MusicBand::getCreationDate)).collect(Collectors.toList());
                break;
            case numberOfParticipants:
                bands = bands.stream().sorted(Comparator.comparing(MusicBand::getNumberOfParticipants)).collect(Collectors.toList());
                break;
            case genre:
                bands = bands.stream().sorted(Comparator.comparing(MusicBand::getGenre)).collect(Collectors.toList());
                break;
            case label:
                Object band = bands.stream().sorted(Comparator.comparing(MusicBand::getLabel)).collect(Collectors.toList());
                bands = (List<MusicBand>) band;
                break;
        }

        return bands;
    }

    public static String[][] getTable(SortBy sort) {
        List<MusicBand> bands = fetchTable();
        bands = sortList(sort, bands);
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
