package models;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Profile {
    public String FullName;
    public String Phone;
    public String Email;
    public String DateOfBirth;
    public Boolean Gender;
    public String Password;

    public static Profile FromResource()
    {
        Properties properties = new Properties();
        InputStream inputStream=null;
        try
        {
            Path currentDir = Paths.get("src", "test", "resources");
            inputStream = new FileInputStream(currentDir + "/data/profile.properties");
            properties.load(inputStream);
            Profile p = new Profile();
            p.FullName = properties.getProperty("FullName");
            p.Phone = properties.getProperty("Phone");
            p.Email = properties.getProperty("Email");
            p.DateOfBirth = properties.getProperty("DateOfBirth");
            p.Gender = Boolean.parseBoolean(properties.getProperty("Gender"));
            p.Password = properties.getProperty("Password");
            inputStream.close();
            return p;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void Save()
    {
        Properties props = new Properties();
        props.put("FullName", this.FullName);
        props.put("Phone", this.Phone);
        props.put("Email", this.Email);
        props.put("DateOfBirth", this.DateOfBirth);
        props.put("Gender", this.Gender.toString());
        props.put("Password", this.Password);

        Path currentDir = Paths.get("src", "test", "resources");
        try {
            FileOutputStream outputStream = new FileOutputStream(currentDir + "/data/profile.properties");
            props.store(outputStream, "");
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
