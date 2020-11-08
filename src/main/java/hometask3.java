import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class hometask3 {




    public static void main(String[] args) throws IOException {
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
