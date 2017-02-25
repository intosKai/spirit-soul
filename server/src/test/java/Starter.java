

public class Starter {
//    @SuppressWarnings("unchecked")
//    public static void main(String[] args) {
//        try {
//            Random random = new Random();
//            Socket socket = new Socket("127.0.0.1", 25565);
//            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
//            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
//            System.out.println("Streams open");
//            System.out.println("Sending netstatus");
//            outputStream.writeByte(NetStatus.INIT);
//            System.out.println("Sending hero type");
//            outputStream.writeByte(HeroClass.DIMA);
//            outputStream.flush();
//            IPlayer player = (IPlayer) inputStream.readObject();
//            System.out.println(player.toString());
//            long time = 0;
//
//            while (true) {
//                time = System.currentTimeMillis();
//                outputStream.writeByte(NetStatus.UPDATE);
//                outputStream.flush();
//                player.setY((short) random.nextInt(Short.MAX_VALUE));
//                player.setX((short) random.nextInt(Short.MAX_VALUE));
//                new Sender(inputStream, outputStream).sendPlayer(player);
//                Map<Byte, IPlayer> map = (Map<Byte, IPlayer>) inputStream.readObject();
//                //System.out.println("Map accepted\n\t " + map.size());
//                map.toString();
//                System.out.println("Данные обновлены за " + (System.currentTimeMillis() - time));
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static class Sender extends Client {
//        public Sender(ObjectInputStream in, ObjectOutputStream out) { super(out, in); }
//        public void sendPlayer(IPlayer player) {
//            try {
//                super.send(player);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public static void main(String[] args) {

    }
}
