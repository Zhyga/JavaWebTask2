package warehouse;

import by.epam.entity.Computer;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ComputerWarehouse {

    private static Set<Computer> computers;

    private ComputerWarehouse() {}

    public static Set<Computer> getComputers() {
        if(computers == null){
            computers = new HashSet<>();
        }
        Computer computer1 = new Computer("C228322Q","CPU","China",1300,true,LocalDateTime.parse("2021-01-30T12:30:00")
                ,new Computer.Type(false,100,true, new String[]{"Socket"}),"accessories","HP");//correct
        Computer computer2 = new Computer("C2283221","GPU","USA",1100,true,LocalDateTime.parse("2021-01-30T12:30:00")
                ,new Computer.Type(false,120,true, new String[]{"PCIex16"}),"accessories","HP");//correct
        Computer computer3 = new Computer("A228322W","HDD","China",130,true,LocalDateTime.parse("2021-01-30T12:30:00")
                ,new Computer.Type(false,20,false, new String[]{"SATA 3","M2"}),"accessories","HP");//correct
        Computer computer4 = new Computer("M228322Q","RAM","Russia",130,true,LocalDateTime.parse("2021-01-30T12:30:00")
                ,new Computer.Type(false,20,false, new String[]{"Chipset"}),"accessories","HP");//correct
        Computer computer5 = new Computer("C1322678Q","Keyboard","China",300,true,LocalDateTime.parse("2021-01-30T12:30:00")
                ,new Computer.Type(true,30,false, new String[]{"USB","PS/2"}),"I/O","HP");//correct
        Computer computer6 = new Computer("N967451P","Headphones","China",350,false,LocalDateTime.parse("2021-01-30T12:30:00")
                ,new Computer.Type(true,10,false, new String[]{"TRS"}),"multimedia","HP");//correct
        Computer computer7 = new Computer("C1236578Z","Mouse","BLR",150,true,LocalDateTime.parse("2021-01-30T12:30:00")
                ,new Computer.Type(true,10,false, new String[]{"USB"}),"I/O","HP");//correct
        Computer computer8 = new Computer("C228323Q","Web-camera","USA",100,false,LocalDateTime.parse("2021-01-30T12:30:00")
                ,new Computer.Type(true,10,false, new String[]{"USB"}),"multimedia","HP");//correct
        Computer computer9 = new Computer("C228324Q","Motherboard","USA",200,true,LocalDateTime.parse("2021-01-30T12:30:00")
                ,new Computer.Type(false,5,false, new String[]{"Socket AM3","DDR3","PCIex16","SATA","COM"}),"accessories","HP");//correct
        Computer computer10 = new Computer("C228325Q","Display","China",170,true,LocalDateTime.parse("2021-01-30T12:30:00")
                ,new Computer.Type(true,100,false, new String[]{"VGA","HDMI"}),"I/O","HP");//correct
        Computer computer11 = new Computer("C228326Q","External hard drive","USA",200,false,LocalDateTime.parse("2021-01-30T12:30:00")
                ,new Computer.Type(true,10,false, new String[]{"USB"}),"accessories","HP");//correct
        Computer computer12 = new Computer("C2283227","Microphone","China",70,false,LocalDateTime.parse("2021-01-30T12:30:00")
                ,new Computer.Type(true,5,false, new String[]{"TRS"}),"I/O","Toshiba");//correct
        Computer computer13 = new Computer("C228328Q","Modem","China",120,false,LocalDateTime.parse("2021-01-30T12:30:00")
               ,new Computer.Type(true,10,false, new String[]{"SFP"}),"accessories","HP");//correct
        Computer computer14 = new Computer("C228329Q","Power supply","USA",246,true,LocalDateTime.parse("2021-01-30T12:30:00")
                ,new Computer.Type(false,300,true, new String[]{"AM3+","LGA1200"}),"accessories","HP");//correct
        Computer computer15 = new Computer("C228330Q","Audio interface","BLR",50,false,LocalDateTime.parse("2021-01-30T12:30:00")
                ,new Computer.Type(true,15,false, new String[]{"PCI","PCLe"}),"multimedia","HP");//correct
        Computer computer16 = new Computer("C228331Q","Controller","Canada",120,false,LocalDateTime.parse("2021-01-30T12:30:00")
                ,new Computer.Type(true,10,false, new String[]{"USB"}),"I/O","HP");
        computers.add(computer1);
        computers.add(computer2);
        computers.add(computer3);
        computers.add(computer4);
        computers.add(computer5);
        computers.add(computer6);
        computers.add(computer7);
        computers.add(computer8);
        computers.add(computer9);
        computers.add(computer10);
        computers.add(computer11);
        computers.add(computer12);
        computers.add(computer13);
        computers.add(computer14);
        computers.add(computer15);
        computers.add(computer16);
        return computers;
    }
}
