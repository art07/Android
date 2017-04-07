package ua.art.myapppackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DataStorage {
    private static final DataStorage DATA_STORAGE = new DataStorage();
    private List<MyApp> appArrayList = new ArrayList<>(Arrays.asList(
            new MyApp(R.string.calculator, R.mipmap.calculator),
            new MyApp(R.string.currency_converter, R.mipmap.currency_converter)));

    private DataStorage() {
    }

    static DataStorage getDataStorage() {
        return DATA_STORAGE;
    }

    List<MyApp> getAppArrayList() {
        return appArrayList;
    }
}
