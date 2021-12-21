package ua.edu.sumdu.j2se.stryzhakov.tasks.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskIO {
    /**
     * Write list to stream. LocalDateTime makes long.
     *
     * @param tasks List for read
     * @param out   Stream for write
     */
    public static void write(AbstractTaskList tasks, OutputStream out) {
        try (DataOutputStream outputStream = new DataOutputStream(out)) {
            outputStream.writeInt(tasks.size());
            for (Task task : tasks) {
                if (task == null) {
                    continue;
                }
                outputStream.writeInt(task.getTitle().length());
                outputStream.writeUTF(task.getTitle());
                outputStream.writeBoolean(task.isActive());
                outputStream.writeInt(task.getRepeatInterval());
                if (task.isRepeated()) {
                    outputStream.writeLong(
                            task.getStartTime()
                                    .atZone(ZoneId.systemDefault())
                                    .toEpochSecond());
                    outputStream.writeLong(
                            task.getEndTime()
                                    .atZone(ZoneId.systemDefault())
                                    .toEpochSecond());
                } else {
                    outputStream.writeLong(
                            task.getTime()
                                    .atZone(ZoneId.systemDefault())
                                    .toEpochSecond());
                }
            }
            outputStream.flush();
        } catch (IOException e) {
            System.out.println("Cannot write to stream");
        }
    }

    /**
     * Write tasks to stream in JSON format.
     *
     * @param tasks List for read
     * @param out   Stream for write
     */

    public static void write(AbstractTaskList tasks, Writer out) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Task> tempList = new ArrayList<>();
        tasks.getStream().filter(Objects::nonNull).forEach(tempList::add);
        try (Writer writer = new BufferedWriter(out)) {
            writer.write(gson.toJson(tempList));
            writer.flush();
        } catch (IOException e) {
            System.out.println("Cannot write to JSON stream");
        }
    }

    /**
     * Read list from stream.
     *
     * @param tasks List for write
     * @param in    Stream for read
     */
    public static void read(AbstractTaskList tasks, InputStream in) {
        try (DataInputStream inputStream = new DataInputStream(in)) {
            Task task;
            int counter = inputStream.readInt();
            for (int i = 0; i < counter; i++) {
                int titleLength = inputStream.readInt();
                String title = inputStream.readUTF();
                boolean active = inputStream.readBoolean();
                int interval = inputStream.readInt();
                if (interval != 0) {
                    LocalDateTime start = LocalDateTime.ofInstant(
                            Instant.ofEpochSecond(inputStream.readLong()),
                            ZoneId.systemDefault());
                    LocalDateTime end = LocalDateTime.ofInstant(
                            Instant.ofEpochSecond(inputStream.readLong()),
                            ZoneId.systemDefault());
                    task = new Task(title, start, end, interval);
                } else {
                    LocalDateTime time = LocalDateTime.ofInstant(
                            Instant.ofEpochSecond(inputStream.readLong()),
                            ZoneId.systemDefault());
                    task = new Task(title, time);

                }
                task.setActive(active);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Cannot read from stream");
        }
    }

    /**
     * Return tasks from JSON stream.
     *
     * @param tasks List for write tasks
     * @param in    JSON stream
     */
    public static void read(AbstractTaskList tasks, Reader in) {
        Type typeTask = new TypeToken<List<Task>>() {}.getType();
        List<Task> tempList = new Gson().fromJson(in, typeTask);
        tempList.stream().filter(Objects::nonNull).forEach(tasks::add);

    }

    /**
     * Write tasks to file.
     *
     * @param tasks List to read
     * @param file  Destination file
     */
    public static void writeBinary(AbstractTaskList tasks, File file) {
        if (!file.exists()) {
            file = new File(file.getName());
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            write(tasks, fos);
        } catch (IOException e) {
            System.out.println("Cannot write to file "
                    + file.getName());
        }
    }

    /**
     * Write tasks to file in JSON format.
     *
     * @param tasks List to read
     * @param file  Destination file
     */
    public static void writeText(AbstractTaskList tasks, File file) {
        if (!file.exists()) {
            file = new File(file.getName());
        }
        try (Writer fw = new FileWriter(file)) {
            write(tasks, fw);
        } catch (IOException e) {
            System.out.println("Cannot write to JSON file "
                    + file.getName());
        }
    }

    /**
     * Read tasks from file.
     *
     * @param tasks List to write
     * @param file  Source file
     */
    public static void readBinary(AbstractTaskList tasks, File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            read(tasks, fis);
        } catch (FileNotFoundException e) {
            System.out.println("File " + file.getName()
                    + " not found");
        } catch (IOException e) {
            System.out.println("Cannot read from file "
                    + file.getName());
        }
    }

    /**
     * Read tasks from JSON file.
     *
     * @param tasks List to write
     * @param file  Source file
     */
    public static void readText(AbstractTaskList tasks, File file) {
        try (FileReader fr = new FileReader(file)) {
            read(tasks, fr);
        } catch (FileNotFoundException e) {
            System.out.println("File " + file.getName()
                    + " not found");
        } catch (IOException e) {
            System.out.println("Cannot read from JSON file "
                    + file.getName());
        }
    }

}
