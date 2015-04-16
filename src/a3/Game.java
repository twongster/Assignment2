package a3;
import a3.Commands.*;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Tiffany Chiapuzio-Wong on 2/19/15.
 * This class instantiates a GameWorld object and processes user input.
 * This class manages the flow of control in the game (controller).
 * This class also display information about the state of the game.
 */
public class Game extends JFrame {
    private GameWorld gw;
    private GameWorldProxy gwp;
    private MapView mv;
    private ScoreView sv;
    private JPanel main;

    public Game(){
        gw = new GameWorld();
        gw.initLayout();
        gwp = new GameWorldProxy(gw);
        mv = new MapView(gwp);
        sv = new ScoreView();

        gwp.addObserver(mv);
        gwp.addObserver(sv);
        establishFrame();

        setLayout(null);

        add(sv, BorderLayout.LINE_START);
        getContentPane().add(mv, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * This method creates the basic layout of the game and adds all the relative
     * components such as buttons, menus, and labels.
     */
    public void establishFrame(){
        setTitle("The Wong Way: 2 Wongz Don't Make A Whight, Part XIV");
        setSize(1000, 825);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(true);
        main = new JPanel();
        main.setSize(1000, 825);
        main.setLocation(0,0);
        createNorth();
        createWest();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    /**
     * This method creates the west panel of the frame, the buttons, as well as assign the keystrokes
     * to their appropriate actions.
     */
    public void createWest(){

        JPanel west = new JPanel();
        west.setLayout(new FlowLayout());
        west.setBackground(Color.green);
        west.setSize(200, 1000);
        west.setLocation(0, 50);
        west.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Commands"));
        InputMap imap = west.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap amap = west.getActionMap();



        JButton tick = new JButton();
        CTick t = CTick.getTick();
        t.setTarget(gw);
        tick.setAction(t);
        tick.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        west.add(tick);

        JButton carCollide = new JButton();
        CCollideCar cc = CCollideCar.getCollideCar();
        cc.setTarget(gw);
        carCollide.setAction(cc);
        carCollide.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        west.add(carCollide);

        JButton pylonCollide = new JButton();
        CCollidePylon cp = CCollidePylon.getCollidePylon();
        cp.setTarget(gw);
        pylonCollide.setAction(cp);
        pylonCollide.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        west.add(pylonCollide);

        JButton birdCollide = new JButton();
        CCollideBird cb = CCollideBird.getCollideBird();
        cb.setTarget(gw);
        birdCollide.setAction(cb);
        birdCollide.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        west.add(birdCollide);

        JButton fuelPickup = new JButton();
        CFuelPickup fp = CFuelPickup.getCollideFuel();
        fp.setTarget(gw);
        KeyStroke fKey = KeyStroke.getKeyStroke('f');
        imap.put(fKey, "fuel pick up");
        amap.put("fuel pick up",fp );
        fuelPickup.setAction(fp);
        fuelPickup.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        west.add(fuelPickup);

        JButton enterOil = new JButton();
        CEnterOilSlick enter = CEnterOilSlick.getEnterOilSlick();
        enter.setTarget(gw);
        enterOil.setAction(enter);
        enterOil.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        west.add(enterOil);

        JButton exitOil = new JButton();
        CExitOilSlick exit = CExitOilSlick.getExitOilSlick();
        exit.setTarget(gw);
        exitOil.setAction(exit);
        exitOil.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        west.add(exitOil);

        JButton quit = new JButton();
        CQuit q = CQuit.getQuit();
        q.setTarget(gw);
        quit.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        KeyStroke qKey = KeyStroke.getKeyStroke('q');
        imap.put(qKey, "quit");
        amap.put("quit", q);
        quit.setAction(q);
        west.add(quit);

        KeyStroke tKey = KeyStroke.getKeyStroke('t');
        imap.put(tKey, "tick");
        amap.put("tick", t);

        CAccelerate a = CAccelerate.getAccelerate();
        a.setTarget(gw);
        KeyStroke aKey = KeyStroke.getKeyStroke("UP");
        imap.put(aKey, "accelerate");
        amap.put("accelerate",a );

        CBrake b = CBrake.getBrake();
        b.setTarget(gw);
        KeyStroke bKey = KeyStroke.getKeyStroke("DOWN");
        imap.put(bKey, "brake");
        amap.put("brake",b );

        CTurnLeft l = CTurnLeft.getLeftTurn();
        l.setTarget(gw);
        KeyStroke lKey = KeyStroke.getKeyStroke("LEFT");
        imap.put(lKey, "left");
        amap.put("left",l );

        CTurnRight r = CTurnRight.getRightTurn();
        r.setTarget(gw);
        KeyStroke rKey = KeyStroke.getKeyStroke("RIGHT");
        imap.put(rKey, "right");
        amap.put("right",r );

        CAddOilSlick o = CAddOilSlick.getAddOilSlick();
        o.setTarget(gw);
        KeyStroke oKey = KeyStroke.getKeyStroke('o');
        imap.put(oKey, "add oil slick");
        amap.put("add oil slick", o);

        JButton changeStrat = new JButton();
        CNewStrategies ns = CNewStrategies.getNewStrategies();
        ns.setTarget(gw);
        KeyStroke nsKey = KeyStroke.getKeyStroke("SPACE");
        imap.put(nsKey, "Change Strategies");
        amap.put("Change Strategies", ns);
        changeStrat.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
        changeStrat.setAction(ns);
        west.add(changeStrat);


        add(west, BorderLayout.LINE_START);
    }

    public void createNorth(){
        JMenuBar bar = new JMenuBar();

        //Menus
        JMenu fileMenu = new JMenu("File");
        JMenu commandMenu = new JMenu("Commands");




        //File Menu Items
        JMenuItem nItem = new JMenuItem("New");
        fileMenu.add(nItem);

        JMenuItem sveItem = new JMenuItem("Save");
        fileMenu.add(sveItem);

        JCheckBoxMenuItem sndItem = new JCheckBoxMenuItem("Sound");
        CSound s = CSound.getSound();
        s.setTarget(gw);
        sndItem.setAction(s);
        fileMenu.add(sndItem);


        JMenuItem abtItem = new JMenuItem("About");
        CAbout a = CAbout.getAbout();
        a.setTarget(gw);
        abtItem.setAction(a);
        fileMenu.add(abtItem);

        JMenuItem qtItem = new JMenuItem("Quit");
        CQuit q = CQuit.getQuit();
        q.setTarget(gw);
        qtItem.setAction(q);

        qtItem.setMnemonic('Q');

        fileMenu.add(qtItem);


        //Command Menu Items
        JMenuItem oilItem = new JMenuItem("Add Oil Slick");
        commandMenu.add(oilItem);
        oilItem.setMnemonic('O');


        JMenuItem fuelItem = new JMenuItem("Pick Up Fuel Can");
        CFuelPickup f = CFuelPickup.getCollideFuel();
        f.setTarget(gw);
        fuelItem.setMnemonic('F');
        fuelItem.setAction(f);
        commandMenu.add(fuelItem);

        JMenuItem colorItem = new JMenuItem("New Colors");
        CNewColors nc = CNewColors.getNewColors();
        nc.setTarget(gw);
        colorItem.setAction(nc);
        commandMenu.add(colorItem);
        colorItem.setMnemonic('N');


        bar.add(fileMenu);
        bar.add(commandMenu);

        UIManager.put("Button.showMnemonics", true);
        setJMenuBar(bar);

    }
}

