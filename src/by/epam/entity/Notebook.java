package by.epam.entity;

import java.time.LocalDateTime;

public class Notebook extends Computer{//only DOM parser
    private int weight;
    private boolean wifi;
    private String display;

    public Notebook() {
    }

    public Notebook(String id, String component, String origin, int price, boolean critical,
                    LocalDateTime date, Type type, String pick, String manufacturer, int weight, boolean wifi, String display) {
        super(id, component, origin, price, critical, date, type, pick, manufacturer);
        this.weight = weight;
        this.wifi = wifi;
        this.display = display;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Notebook notebook = (Notebook) obj;
        return weight == notebook.weight &&
                wifi == notebook.wifi &&
                display == notebook.display || (display != null && display.equals(notebook.display));
    }

    @Override
    public int hashCode() {
        int result = 127;
        result = 11 * result + weight;
        result = 11 * result + (wifi ? 1 : 0);
        result = 11 * result + (display == null ? 0 : display.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("weight=" );
        sb.append(weight + "\n");
        sb.append("wifi=" );
        sb.append(wifi + "\n");
        sb.append("display=" );
        sb.append(display + "\n");
        return sb.toString();
    }
}
