package ua.art.myapppackage;

class MyApp {
    private int appName;
    private int imageId;

    MyApp(int appName, int imageId) {
        this.appName = appName;
        this.imageId = imageId;
    }

    int getAppName() {
        return appName;
    }

    int getImageId() {
        return imageId;
    }
}
