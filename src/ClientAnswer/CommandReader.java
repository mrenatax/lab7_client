package ClientAnswer;

import ClientReceiver.ReceiveDataFromServer;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.nio.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import static ClientAnswer.ComplicatedCommands.*;

public class CommandReader implements Runnable {
    Thread thread;
    private boolean isConnected = false;
    ReentrantLock locker;
    public CommandReader() {
        locker = new ReentrantLock(); // создаем блокировку
        thread=new Thread(this, "Поток клиента с отправкой команд");
        thread.start(); //запускаем поток
    }
    private SocketChannel createChannel() throws IOException {
        ByteBuffer bf = ByteBuffer.allocate(16384);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(true);
        SocketAddress socketAddress = new InetSocketAddress("localhost", 55665);
        try {
            socketChannel.connect(socketAddress);
        }catch (ConnectException e){
            createChannel();
        }
        return socketChannel;
    }
    @Override
    public void run() {
        while (!isConnected) {
            isConnected = true;
            ArrayList<String> history = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);
            String commandName = "";
            System.out.println("Введите команду для выполнения. Чтобы узнать все доступные команды введите 'help'");
            try {
                    while (!commandName.equals("exit")) {
                        while (!thread.isInterrupted()) {
                            commandName = scanner.nextLine();
                            commandName = commandName.trim();
                            String[] commandsArray = commandName.trim().split(" ", 2);
                            switch (commandsArray[0]) {
                                case "info":
                                    history.add(commandName);
                                    ComplicatedObject info = new ComplicatedObject("info");
                                    SocketChannel socketchannel = createChannel();
                                    ObjectOutputStream outputStream = new ObjectOutputStream(socketchannel.socket().getOutputStream());
                                    outputStream.writeObject(info);
                                    ReceiveDataFromServer r = new ReceiveDataFromServer();
                                    r.receive();
                                    socketchannel.close();
                                    outputStream.close();
                                    break;
                                case "help":
                                    history.add(commandName);
                                    ComplicatedObject help = new ComplicatedObject("help");
                                    SocketChannel socketchannel1 = createChannel();
                                    ObjectOutputStream outputStream1 = new ObjectOutputStream(socketchannel1.socket().getOutputStream());
                                    outputStream1.writeObject(help);
                                    ReceiveDataFromServer r3 = new ReceiveDataFromServer();
                                    r3.receive();
                                    socketchannel1.close();
                                    outputStream1.close();
                                    break;
                                case "head":
                                    history.add(commandName);
                                    ComplicatedObject head = new ComplicatedObject("head");
                                    SocketChannel socketchannel2 = createChannel();
                                    ObjectOutputStream outputStream2 = new ObjectOutputStream(socketchannel2.socket().getOutputStream());
                                    outputStream2.writeObject(head);
                                    ReceiveDataFromServer r1 = new ReceiveDataFromServer();
                                    r1.receive();
                                    socketchannel2.close();
                                    outputStream2.close();
                                    break;
                                case "clear":
                                    System.out.println("Если у Вас уже есть учётная запись введите логин." +
                                            "\n" + "Если у Вас ещё нет учётной записи, необходимо её создать: для этого придумайте логин. " +
                                            "\n" + "Ваш логин:");
                                    Authorization authorization = new Authorization();
                                    authorization.authorize();
                                    ReceiveDataFromServer r2 = new ReceiveDataFromServer();
                                    r2.receive();
                                    SocketChannel socketchannel3 = createChannel();
                                    ObjectOutputStream outputStream3 = new ObjectOutputStream(socketchannel3.socket().getOutputStream());
                                    history.add(commandName);
                                    ComplicatedObject clear = new ComplicatedObject("clear");
                                    outputStream3.writeObject(clear);
                                    r2.receive();
                                    socketchannel3.close();
                                    outputStream3.close();
                                    break;
                                case "show":
                                    SocketChannel socketchannel4 = createChannel();
                                    ObjectOutputStream outputStream4 = new ObjectOutputStream(socketchannel4.socket().getOutputStream());
                                    history.add(commandName);
                                    ComplicatedObject show = new ComplicatedObject("show");
                                    outputStream4.writeObject(show);
                                    ReceiveDataFromServer r4 = new ReceiveDataFromServer();
                                    r4.receive();
                                    socketchannel4.close();
                                    outputStream4.close();
                                    break;
                                case "add":
                                    System.out.println("Если у Вас уже есть учётная запись введите логин." +
                                            "\n" + "Если у Вас ещё нет учётной записи, необходимо её создать: для этого придумайте логин. " +
                                            "\n" + "Ваш логин:");
                                    Authorization authorization1 = new Authorization();
                                    authorization1.authorize();
                                    ReceiveDataFromServer r5 = new ReceiveDataFromServer();
                                    r5.receive();
                                    history.add(commandName);
                                    System.out.println("Введите имя NAME нового SpaceMarine:");
                                    ComplicatedObject co = new ComplicatedObject("add", add());
                                    SocketChannel socketchannel5 = createChannel();
                                    ObjectOutputStream outputStream5 = new ObjectOutputStream(socketchannel5.socket().getOutputStream());
                                    outputStream5.writeObject(co);
                                    r5.receive();
                                    socketchannel5.close();
                                    outputStream5.close();

                                    break;
                                case "update_id":
                                    System.out.println("Если у Вас уже есть учётная запись введите логин." +
                                            "\n" + "Если у Вас ещё нет учётной записи, необходимо её создать: для этого придумайте логин. " +
                                            "\n" + "Ваш логин:");
                                    Authorization authorization2 = new Authorization();
                                    authorization2.authorize();
                                    ReceiveDataFromServer r6 = new ReceiveDataFromServer();
                                    r6.receive();
                                    history.add(commandName);
                                    ComplicatedObject obj = new ComplicatedObject("update_id", updateIdFirstPart(), updateIdSecondPart());
                                    SocketChannel socketchannel6 = createChannel();
                                    ObjectOutputStream outputStream6 = new ObjectOutputStream(socketchannel6.socket().getOutputStream());
                                    outputStream6.writeObject(obj);
                                    r6.receive();
                                    outputStream6.close();
                                    socketchannel6.close();
                                    break;
                                case "remove_by_id":
                                    System.out.println("Если у Вас уже есть учётная запись введите логин." +
                                            "\n" + "Если у Вас ещё нет учётной записи, необходимо её создать: для этого придумайте логин. " +
                                            "\n" + "Ваш логин:");
                                    Authorization authorization3 = new Authorization();
                                    authorization3.authorize();
                                    ReceiveDataFromServer r7 = new ReceiveDataFromServer();
                                    r7.receive();
                                    history.add(commandName);
                                    ComplicatedObject remove = new ComplicatedObject("remove_by_id", removeById());
                                    SocketChannel socketchannel7 = createChannel();
                                    ObjectOutputStream outputStream7 = new ObjectOutputStream(socketchannel7.socket().getOutputStream());
                                    outputStream7.writeObject(remove);
                                    r7.receive();
                                    outputStream7.close();
                                    socketchannel7.close();
                                    break;
                                case "sum_of_height":
                                    SocketChannel socketchannel8 = createChannel();
                                    ObjectOutputStream outputStream8 = new ObjectOutputStream(socketchannel8.socket().getOutputStream());
                                    history.add(commandName);
                                    ComplicatedObject heightSum = new ComplicatedObject("sum_of_height");
                                    outputStream8.writeObject(heightSum);
                                    ReceiveDataFromServer r8 = new ReceiveDataFromServer();
                                    r8.receive();
                                    outputStream8.close();
                                    socketchannel8.close();
                                    break;
                                case "max_by_name":
                                    SocketChannel socketchannel9 = createChannel();
                                    ObjectOutputStream outputStream9 = new ObjectOutputStream(socketchannel9.socket().getOutputStream());
                                    history.add(commandName);
                                    ComplicatedObject maxName = new ComplicatedObject("max_by_name");
                                    outputStream9.writeObject(maxName);
                                    ReceiveDataFromServer r9 = new ReceiveDataFromServer();
                                    r9.receive();
                                    outputStream9.close();
                                    socketchannel9.close();
                                    break;
                                case "filter_greater_than_height":
                                    SocketChannel socketchannel10 = createChannel();
                                    ObjectOutputStream outputStream10 = new ObjectOutputStream(socketchannel10.socket().getOutputStream());
                                    history.add(commandName);
                                    ComplicatedObject filter = new ComplicatedObject("filter_greater_than_height", filterGreater());
                                    outputStream10.writeObject(filter);
                                    ReceiveDataFromServer r10 = new ReceiveDataFromServer();
                                    r10.receive();
                                    outputStream10.close();
                                    socketchannel10.close();
                                    break;
                                case "remove_greater":
                                    System.out.println("Если у Вас уже есть учётная запись введите логин." +
                                            "\n" + "Если у Вас ещё нет учётной записи, необходимо её создать: для этого придумайте логин. " +
                                            "\n" + "Ваш логин:");
                                    Authorization authorization4 = new Authorization();
                                    authorization4.authorize();
                                    ReceiveDataFromServer r11 = new ReceiveDataFromServer();
                                    r11.receive();
                                    SocketChannel socketchannel11 = createChannel();
                                    ObjectOutputStream outputStream11 = new ObjectOutputStream(socketchannel11.socket().getOutputStream());
                                    history.add(commandName);
                                    ComplicatedObject removeGr = new ComplicatedObject("remove_greater", removeGreater());
                                    outputStream11.writeObject(removeGr);
                                    r11.receive();
                                    outputStream11.close();
                                    socketchannel11.close();
                                    break;
                                case "history":
                                    SocketChannel socketchannel12 = createChannel();
                                    ObjectOutputStream outputStream12 = new ObjectOutputStream(socketchannel12.socket().getOutputStream());
                                    history.add(commandName);
                                    ComplicatedObject history1 = new ComplicatedObject("history", history.toString());
                                    outputStream12.writeObject(history1);
                                    ReceiveDataFromServer r12 = new ReceiveDataFromServer();
                                    r12.receive();
                                    outputStream12.close();
                                    socketchannel12.close();
                                    break;
                                case "exit":
                                    SocketChannel socketchanne101 = createChannel();
                                    ObjectOutputStream outputStream101 = new ObjectOutputStream(socketchanne101.socket().getOutputStream());
                                    ComplicatedObject exit = new ComplicatedObject("exit");
                                    outputStream101.writeObject(exit);
                                    System.out.println("--------------------------------------\nКлиент завершил работу программы.\n"+
                                            "┊┊╱▔▔╲┊┊┈┈┈┈┈┈┈┈\n" +
                                            "▂╱╲╭▅▕┊┊┈┈┈┈┈┈┈┈\n" +
                                            "╲━╯▏┈▕┊┊┊┈┈┈┈┈┈┈\n" +
                                            "┊▔▔▏┈╱┊┊┊┊┊┊┊┊╱╲\n" +
                                            "┈┈╱╯▕▔▔▔▔▔▔▔▔▔▏▕\n" +
                                            "╯╯▏╯╯╲╲╲╲╲╲╲╲╱╰▕\n" +
                                            "╯╯▏╯╯╯▔▔▔▔▔▔▔╰╰▕\n" +
                                            "╯╯╲▂▂▂▂▂▂▂▂▂▂▂▂╱");
                                    outputStream101.close();
                                    socketchanne101.close();
                                    break;
                                case "execute_script":
                                    history.add(commandName);
                                    ComplicatedObject executeScript = new ComplicatedObject("execute_script");
                                    SocketChannel socketchanne100 = createChannel();
                                    ObjectOutputStream outputStream100 = new ObjectOutputStream(socketchanne100.socket().getOutputStream());
                                    outputStream100.writeObject(executeScript);
                                    ReceiveDataFromServer r13 = new ReceiveDataFromServer();
                                    r13.receive();
                                    outputStream100.close();
                                    socketchanne100.close();
                                    break;
                                default:
                                    System.out.println("Такой команды нет, повторите ввод. Чтобы узнать список доступных команд введите help.");
                            }
                        }
                    }

                } catch (IllegalStateException e) {
                System.out.println("Программа завершена");
            } catch (NoSuchElementException e) {
                e.getMessage();
            } catch (IOException | InterruptedException e) {
                System.out.println("Удаленный хост принудительно разорвал существующее подключение. Клиенту необходимо повторить подключение.");
                e.printStackTrace();
            } finally {
                thread.interrupt();
            }
        }
    }




}
