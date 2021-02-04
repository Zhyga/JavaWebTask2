package by.epam.entity;

import java.time.LocalDateTime;

public class Computer {
    private String id;
    private String component;
    private String origin;
    private int price;
    private boolean critical;
    private LocalDateTime date;
    private Type type = new Type();
    private String pick;
    private String manufacturer;

    public Computer() {
    }

    public Computer(String id, String component, String origin, int price, boolean critical, LocalDateTime date, Type type, String pick, String manufacturer) {
        this.id = id;
        this.component = component;
        this.origin = origin;
        this.price = price;
        this.critical = critical;
        this.date = date;
        this.type = type;
        this.pick = pick;
        this.manufacturer = manufacturer;
    }

    public String getId() {
        return id;
    }

    public String getComponent() {
        return component;
    }

    public String getOrigin() {
        return origin;
    }

    public int getPrice() {
        return price;
    }

    public boolean isCritical() {
        return critical;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Type getType() {
        return type;
    }

    public String getPick() {
        return pick;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPick(String pick) {
        this.pick = pick;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nComputer{").append("\nid = ").append(id);
        sb.append("\ncomponent = ").append(component);
        sb.append("\norigin = ").append(origin);
        sb.append("\nprice = ").append(price);
        sb.append("\ncritical = ").append(critical);
        sb.append("\ndate = ").append(date);
        sb.append("\npick = ").append(pick);
        sb.append("\nmanufacturer = ").append(manufacturer).append("}");
        sb.append("\n").append(type);
        sb.append("\n");
        return sb.toString();
    }

    public class Type{
        private boolean peripheral;
        private int energy;
        private boolean cooler;
        private String[] ports;

        public Type() {
        }

        public Type(boolean peripheral, int energy, boolean cooler, String[] ports) {
            this.peripheral = peripheral;
            this.energy = energy;
            this.cooler = cooler;
            this.ports = ports;
        }

        public boolean isPeripheral() {
            return peripheral;
        }

        public void setPeripheral(boolean peripheral) {
            this.peripheral = peripheral;
        }

        public int getEnergy() {
            return energy;
        }

        public void setEnergy(int energy) {
            this.energy = energy;
        }

        public boolean isCooler() {
            return cooler;
        }

        public void setCooler(boolean cooler) {
            this.cooler = cooler;
        }

        public String[] getPorts() {
            return ports;
        }

        public void setPorts(String[] ports) {
            this.ports = ports;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Type{");
            sb.append("\n\tperipheral=").append(peripheral);
            sb.append("\n\tenergy=").append(energy);
            sb.append("\n\tcooler=").append(cooler);
            sb.append("\n\tports=");
            for (int i = 0; i < ports.length; i++) {
                sb.append(ports[i]).append(" ");
            }
            sb.append("\n\t}");
            return sb.toString();
        }
    }
}
