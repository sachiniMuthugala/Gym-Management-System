package sample;

import java.io.*;
import java.util.ArrayList;

//otherwise when the programme runs again all the past data will disappear

public class DataHistory {
    public static void storeData() {

        ArrayList<DefaultMember> storeDataArray = MyGymManager.defaultMember(); //getting the array as the object
        File file=new File("data.txt");

        //File output stream is a binary stream
        FileOutputStream fileOutput = null;
        //for primitive data types
        ObjectOutputStream objectOutput = null;
        try {
            //writing data to a file
            fileOutput = new FileOutputStream(file);
            //writing primitive data types to a file
            objectOutput = new ObjectOutputStream(fileOutput);

            //getting data from array
            for (int i = 0; i < storeDataArray.size(); i++) {
                objectOutput.writeObject(storeDataArray.get(i));
            }

        } catch (FileNotFoundException e) {
            //if file isn't exist in the project
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutput.close();
                objectOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public static ArrayList<DefaultMember> readData() {

        //Reading objects from Array list
        ArrayList<DefaultMember> readDataArray =new ArrayList<>();
        File file=new File("data.txt");

        //File input stream is a binary stream
        FileInputStream fileInput = null;

        //for primitive data types
        ObjectInputStream objectInput = null;
        try {

            //reading binary data
            fileInput = new FileInputStream(file);
            //reading objects from stream
            objectInput = new ObjectInputStream(fileInput);

            //add to an array
            while (true){
                readDataArray.add((DefaultMember) objectInput.readObject());
            }


        }catch (EOFException e) {
        }  catch (FileNotFoundException e) {
        e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();

        } finally {
            try {
                fileInput.close();
                objectInput.close();
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        return readDataArray;
    }
}


