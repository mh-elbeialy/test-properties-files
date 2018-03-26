package test.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * @author Mohamed Elbeialy
 */

public class Test {
	public static void main(String[] args) {
		try {
			File[] paths = null;
			InputStream input;
			List<String> errors = new ArrayList<>();
			Properties prop = new Properties();
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Enter the folder that contains properties files");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				paths = selectedFile.listFiles();
			}
			for (File f : paths) {
				try {
					if (f.getName().endsWith(".properties")) {
						input = new FileInputStream(f);
						prop.load(input);
						Enumeration<?> e = prop.propertyNames();
						while (e.hasMoreElements()) {
							String key = (String) e.nextElement();
							String value = prop.getProperty(key);
						}
					}
				} catch (Exception e2) {
					errors.add(f.getName());
					continue;
				}
			}
			if (errors.size() == 0)
				JOptionPane.showMessageDialog(null, "No erros!");
			else {
				StringBuffer errosString = new StringBuffer();
				for (String e : errors) {
					errosString.append(e);
					errosString.append(",");
				}
				JOptionPane.showMessageDialog(null,
						"There's (" + errors.size() + ") erros in the following files: " + errosString);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error Occured! try again ..");
		}
	}
}
