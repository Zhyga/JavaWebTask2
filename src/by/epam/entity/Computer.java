package by.epam.entity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

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
    private static final String DEFAULT_MANUFUCTURER = "HP";

    public Computer() {
        manufacturer = DEFAULT_MANUFUCTURER;
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Computer computer = (Computer) obj;
        return price == computer.price &&
                critical == computer.critical &&
                Objects.equals(id, computer.id) &&
                Objects.equals(component, computer.component) &&
                Objects.equals(origin, computer.origin) &&
                Objects.equals(date, computer.date) &&
                Objects.equals(type, computer.type) &&
                Objects.equals(pick, computer.pick) &&
                Objects.equals(manufacturer, computer.manufacturer);
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 24 * result + (component == null ? 0 : component.hashCode());
        result = 24 * result + (id == null ? 0 : id.hashCode());
        result = 24 * result + (origin == null ? 0 : origin.hashCode());
        result = 24 * result + (pick == null ? 0 : pick.hashCode());
        result = 24 * result + (manufacturer == null ? 0 : manufacturer.hashCode());
        result = 24 * result + price;
        result = 24 * result + (critical ? 1 : 0 );
        result = 24 * result + (date == null ? 0 : date.hashCode());
        result = 24 * result + type.hashCode();
        return result;
    }

    public static class Type{
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
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            Type type = (Type) obj;
            return peripheral == type.peripheral &&
                    energy == type.energy &&
                    cooler == type.cooler &&
                    Arrays.equals(ports, type.ports);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 37 * result + (peripheral ? 1 : 0);
            result = 37 * result + energy;
            result = 37 * result + (cooler ? 0 : 1);
            for(String str : ports)
                result = 37 * result + (str != null ? str.hashCode() : 0);
            return result;
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
