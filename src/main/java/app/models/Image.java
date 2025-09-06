package app.models;

public class Image {

    private String download_url;
    private String localPath; // caminho local para o template

    // getters e setters
    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String url) {
        this.download_url = url;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
