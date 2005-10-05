package org.ftp4che.util.ftpfile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.ftp4che.commands.Command;

public class FTPFile implements Comparable {

    public static final int UNKNOWN = -1;

    public final static int UNIX = 0;

    public static final int WINDOWS = 1;

    public final static int VMS = 2;
    
    public final static int NETWARE = 3;

    private int fileType;

    private String transferType = Command.TYPE_I;

    private final static SimpleDateFormat formatter = new SimpleDateFormat(
            "dd-MM-yyyy HH:mm");

    protected boolean isLink = false;

    protected int linkCount = 1;

    protected String mode;

    protected boolean directory = false;

    protected long size = -1;

    protected String name;

    protected String path;

    protected String linkedname;

    protected String owner;

    protected String group;

    protected Date date;

    protected String serverString;

    public FTPFile(String path, String name, boolean directory) {
        this.name = name;
        this.path = path;
        this.directory = directory;
    }

    public FTPFile(String path, String name) {
        this(path, name, false);
    }

    public FTPFile(File file) {
        this.name = file.getName();
        setPath(file.getParent());
        this.size = file.length();
        String mode = "";

        if (file.isFile())
            mode += "-";
        else
            mode += "d";
        if (file.canRead())
            mode += "r";
        else
            mode += "-";
        if (file.canWrite())
            mode += "w";
        else
            mode += "-";

        setMode(mode);
    }

    public FTPFile(int type, String path, String name, String serverString) {
        this.fileType = type;
        this.serverString = serverString;
        this.name = name;
        this.path = path;
    }

    public int getFileType() {
        return fileType;
    }

    public String getGroup() {
        return group;
    }

    public boolean isDirectory() {
        return directory;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getServerString() {
        return serverString;
    }

    public long size() {
        return size;
    }

    public String getMode() {
        return mode;
    }

    public boolean isLink() {
        return isLink;
    }

    public int getLinkCount() {
        return linkCount;
    }

    public String getLinkedname() {
        return linkedname;
    }

    void setGroup(String group) {
        this.group = group;
    }

    void setDirectory(boolean directory) {
        this.directory = directory;
    }

    void setLink(boolean isLink) {
        this.isLink = isLink;
    }

    void setLinkedName(String linkedname) {
        this.linkedname = linkedname;
    }

    void setOwner(String owner) {
        this.owner = owner;
    }

    void setMode(String mode) {
        this.mode = mode;
    }

    public void setLinkCount(int linkCount) {
        this.linkCount = linkCount;
    }

    public String toString() {
        if (path != null && path.length() > 0)
            if (path.charAt(path.length() - 1) == '/')
                return path + name;
            else
                return path + "/" + name;
        return name;
    }

    public int compareTo(Object o) {
        FTPFile to = (FTPFile) o;

        if (this.isDirectory() && to.isDirectory())
            return this.getName().compareTo(to.getName());
        else if (this.isDirectory() && to.isDirectory())
            return this.getName().compareTo(to.getName());
        else if (this.isDirectory())
            return 1;

        return 0;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public File getFile() {
        return new File(getPath(), getName());
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
