/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.opendfa.GUI;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author terasud
 */
public class DfaFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        Boolean result = file.canRead();
        Pattern p = Pattern.compile("\\w*(.dfa|.nfa)");
        Matcher m = p.matcher(file.getName());
        result &= (m.find() || file.isDirectory());
        return result;
    }

    @Override
    public String getDescription() {
        return ".dfa";
    }

}
