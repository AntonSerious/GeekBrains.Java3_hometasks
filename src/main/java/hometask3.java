import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class hometask3 {




    public static void main(String[] args) throws IOException {
//           File file =  new File("123");

           //file.mkdirs();
//        String[] files = file.list();
//        for (String name: files){
//            System.out.println(name);
//        }

//        File file =  new File("123");
//        String[] files = file.list(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.startsWith("java");
//            }
//        });
//        System.out.println(Arrays.asList(files));
//
//        System.out.println(file.getParent());
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        long t = System.currentTimeMillis();
//        try(FileInputStream in = new FileInputStream("123/test.txt")){
//            byte[] arr = new byte[512];
//            int x;
//            while((x = in.read()) != -1){
//                System.out.println(x);
//            }
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
       // System.out.println(System.currentTimeMillis() - t);
//        long t = System.currentTimeMillis();
//        try(InputStreamReader isr = new InputStreamReader(new FileInputStream("123/test.txt"), "UTF-8")){
//            byte[] arr = new byte[512];
//            int x;
//            while((x = isr.read()) != -1){
//                System.out.print((char) x);
//            }
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//        System.out.println(System.currentTimeMillis() - t);
//        String filename = "123/test.txt";
//
//        BufferedReader br =null;
//        FileReader fr = null;
//
//        try{
//            fr = new FileReader(filename);
//            br = new BufferedReader(fr);
//
//            String currentLine;
//
//            while((currentLine = br.readLine())!=null){
//                System.out.println(currentLine);
//            }
//        } catch(FileNotFoundException e){
//            e.printStackTrace();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//        finally {
//            try{
//                if(br != null){
//                    br.close();
//                }
//                if(fr != null){
//                    fr.close();
//                }
//            } catch(IOException e){
//                e.printStackTrace();
//            }
//        }
//        PipedInputStream in = null;
//        PipedOutputStream out = null;
//        try {
//            in = new PipedInputStream();
//            out = new PipedOutputStream();
//            out.connect(in);
//            for (int i = 0; i < 100; i++) {
//                out.write(i);
//            }
//
//            int x;
//            while((x = in.read()) !=-1){
//                System.out.println(x + " ");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ArrayList<InputStream> ali = new ArrayList<>();
//        ali.add(new FileInputStream("123/java.txt"));
//        ali.add(new FileInputStream("123/test.txt"));
//        ali.add(new FileInputStream("123/test_1.txt"));
//        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ali));
//
//        int x;
//        while ( (x = in.read()) != -1){
//            System.out.print((char)x);
//        }
//        try(RandomAccessFile raf = new RandomAccessFile("123/java.txt", "r")){
//            raf.seek(2);
//            System.out.println((char) raf.read());
//            raf.seek(0);
//            System.out.println((char) raf.read());
//        }

        //Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
        File file = new File("123/task1.txt");
        try(FileInputStream fis = new FileInputStream(file)){
            byte[] arr = new byte[512];
            int x;
            while((x = fis.read(arr)) != -1){
                System.out.println(new String(arr, 0, x, "UTF-8"));
            }
        }
        //Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
        // Может пригодиться следующая конструкция: ArrayList<InputStream> al = new ArrayList<>(); ... Enumeration<InputStream> e = Collections.enumeration(al);
        ArrayList<InputStream> ali = new ArrayList<>();
        ali.add(new FileInputStream("123/file1_to_unite.txt"));
        ali.add(new FileInputStream("123/file2_to_unite.txt"));
        ali.add(new FileInputStream("123/file3_to_unite.txt"));
        ali.add(new FileInputStream("123/file4_to_unite.txt"));
        ali.add(new FileInputStream("123/file5_to_unite.txt"));
        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ali));
        int x;
        while ( (x = in.read()) != -1){
            System.out.print((char)x);
        }
        //3.Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
        // Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
        // Контролируем время выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.
    //Создаю файл сам. Хотел сделать так, чтобы i-ая страница состояла из слов page+i. Но из-за того, что i увеличивался, наполнение страниц "поплыло"
//        StringBuilder sb = new StringBuilder();
//        byte[] outData = new byte[1800];

//        for (int i = 0; i < 5000; i++) { //число страниц
//            for (int k = 0; k < 350; k++) {
//                sb.append("Page" + i);
//            }
//
//            outData = sb.append("\n"+"--------"+ "\n").toString().getBytes();
//            try (FileOutputStream fos = new FileOutputStream(new File("123/task3.txt"))) {
//                fos.write(outData);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        //FileInputStream fis3 = new FileInputStream(new File("123/task3.txt"));
        String page = new String();
        int intPage;

        try (RandomAccessFile raf = new RandomAccessFile("123/task3.txt", "r")) {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                System.out.println("\n"+"Choose the page to show. Enter '-1' to stop application:");
                page = bf.readLine();
                intPage = Integer.parseInt(page);
                if (intPage == -1){
                    break;
                }
                raf.seek(1800 * intPage); //примерно отсчитываю нужную страницу
                for (int i = 0; i < 1800; i++) {
                    System.out.print((char) raf.read());
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
