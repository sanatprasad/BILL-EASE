import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class BillEase {

    public static void main(String args[]) throws IOException {       
        try {
            Calendar calendar = Calendar.getInstance();
            String folderName = new SimpleDateFormat("MMMM").format(calendar.getTime());
            // CREATING 'MENU' FILE
            File folder = new File(folderName);
            if (!folder.exists()) {
                folder.mkdir();
            }
            File menu = new File("./menu.txt");
            
            // WRITING TO FILE 'MENU'
            
            FileWriter write_menu = new FileWriter("./menu.txt");
            write_menu.write("Idli 10\n");
            write_menu.write("Dosa 32\n");
            write_menu.write("Roti 7\n");
            write_menu.write("Rice 12\n");
            write_menu.write("Dal 12");

            write_menu.close();
            
            // TAKING CUSTOMER ORDER
            // NAME
            Scanner scan_name = new Scanner(System.in);
            System.out.println("Enter Name: ");
            String name = scan_name.nextLine();

            // ITEM
            int sn = 1;
            Scanner scan_item = new Scanner(System.in);
            Scanner scan_quantity = new Scanner(System.in);
            
            File billNumber=new File("./bill_no.txt");
            if (billNumber.createNewFile()) {
                System.out.println("Please Enter You Order");
            } else {
                System.out.println("Please Enter You Order");
            }
            int billdata=0;
            System.out.println("Here is the Menu");
            System.out.println("................");
            System.out.println("Idli -Rs 10\n");
            System.out.println("Dosa -Rs 32\n");
            System.out.println("Roti -Rs 17\n");
            System.out.println("Rice -Rs 32\n");
            System.out.println("Dal  -Rs 32\n");

            Scanner billread = new Scanner(billNumber);
            while (billread.hasNextLine()){
                billdata=Integer.parseInt(billread.next());
            }
            
            FileWriter billn_write =new FileWriter(billNumber);
            billn_write.write(String.valueOf(billdata+1));
            billread.close();
            billn_write.close();
          

            // CREATING BILL TEMPLATE
            File bill = new File("./" + folderName + "/bill_" + String.valueOf(billdata+1) + ".txt");
            FileWriter write_bill = new FileWriter(bill);
            // writing the content into the FileOperationExample.txt file
            write_bill.write("........................................\n");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            write_bill.write(".............BILL NUMBER :"+String.valueOf(billdata+1)+"...........\n");
            write_bill.write("WELCOME " + name + "      " + formatter.format(date) + "\n");

            write_bill.write("........................................\n");
            write_bill.write("                  BILL                  \n");
            write_bill.write("........................................\n");
            write_bill.write("|    ITEM    |     COST    |     QTY   |\n");
            write_bill.write("........................................\n");
            int total=0;
            int quantity;
            while (true) {
                System.out.print("Enter item name (type 'quit' to exit): ");
                String itemName = scan_item.nextLine();
                if (itemName.equals("quit")) {
                break;
                }
                System.out.print("Enter Quantity");
                quantity=scan_quantity.nextInt();
                Scanner menuReader = new Scanner(menu);
                while (menuReader.hasNextLine()) {
                
                String fileData = menuReader.next();
                
                if (fileData.equals(itemName)) {
                    int cost=Integer.parseInt(menuReader.next());
                    write_bill.write(sn+".    "+fileData+"     "+"     "+cost+"      "+"     "+quantity+"      \n");
                    write_bill.write("........................................\n");
                    total=total+cost*quantity;
                }
                }
                sn=sn+1;
                menuReader.close();
            }
    
                    double tax=0.18*total;
                    double grand_total=total+tax;
                    write_bill.write("                          TOTAL: Rs "+total+"/-"+"\n");
                    write_bill.write("               TAX (GST-18%): Rs "+Math.round(tax)+"/-"+"\n");
                    write_bill.write("                    GRAND TOTAL: Rs "+Math.round(grand_total)+"/-"+"\n");
                    write_bill.write("........................................\n");
                    write_bill.write("               |THANK YOU!|             \n");
                    write_bill.write("      |YOUR ORDER HAS BEEN PLACED!|     \n");
                    write_bill.write("........................................\n");
            write_bill.close();
            
            scan_name.close();
            scan_item.close();
            scan_quantity.close();
        }
        catch (FileNotFoundException exception) {
            System.out.println("Unexcpected error occurred!");
            exception.printStackTrace();
        } 
        catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
          

        }

            
} 
