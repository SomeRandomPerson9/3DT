package com.harry9137.api.util;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.IndexedMesh;
import com.bulletphysics.collision.shapes.TriangleIndexVertexArray;
import com.harry9137.api.main.Launch;
import com.harry9137.api.physics.CollisionMesh;
import com.harry9137.api.render.*;
import com.harry9137.api.render.math.Vector3f;
import com.harry9137.api.scenes.SceneFile;
import javafx.scene.shape.TriangleMesh;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ResourceLoader {
    public static Texture loadTexture(String fileName){
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length-1];
        try{
            int id = TextureLoader.getTexture(ext, new FileInputStream(new File("./res/textures/" + fileName))).getTextureID();
            return new Texture(id);

        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static String loadShader(String fileName){
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;
        try{
            shaderReader = new BufferedReader(new FileReader("./res/shaders/" + fileName));
            String line;
            while((line = shaderReader.readLine()) != null){
                shaderSource.append(line).append("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
        return shaderSource.toString();
    }
    public static Mesh loadMesh(String fileName){
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length-1];
        if(!ext.equals("obj")){
            System.err.println("Error: File format not supported for mesh data: " + ext);
            new Exception().printStackTrace();
            System.exit(1);
        }

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Integer> integers = new ArrayList<Integer>();
        int drawType = GL11.GL_TRIANGLES;
        BufferedReader meshReader = null;

        try{
            meshReader = new BufferedReader(new FileReader("./res/models/" + fileName));
            String line;
            while((line = meshReader.readLine()) != null){
                String[] tokens = line.split(" ");
                tokens = Util.removeEmptyStrings(tokens);

                if(tokens.length == 0 || tokens[0].equals("#")){
                    continue;
                }
                else if(tokens[0].equals("v")){
                    vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]), Float.valueOf(tokens[2]), Float.valueOf(tokens[3]))));
                }
                else if(tokens[0].equals("f")){
                    integers.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                    integers.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                    integers.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                    if(tokens.length > 4){
                        integers.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                        integers.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                        integers.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
                    }
                }
            }
            meshReader.close();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
        Mesh res = new Mesh();
        Vertex[] resV = new Vertex[vertices.size()];
        Integer[] resI = new Integer[integers.size()];
        vertices.toArray(resV);
        integers.toArray(resI);
        res.addVertices(resV, Util.toIntArray(resI));
        System.out.println(vertices);
        System.out.println(integers);
        return res;
    }
    public static Mesh loadMesh(InputStream stream){
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Integer> integers = new ArrayList<Integer>();
        BufferedReader meshReader = null;
        File file  = inputStreamToFile(stream);

        try{
            meshReader = new BufferedReader(new FileReader(file));
            String line;
            while((line = meshReader.readLine()) != null){
                String[] tokens = line.split(" ");
                tokens = Util.removeEmptyStrings(tokens);

                if(tokens.length == 0 || tokens[0].equals("#")){
                    continue;
                }
                else if(tokens[0].equals("v")){
                    vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]), Float.valueOf(tokens[2]), Float.valueOf(tokens[3]))));
                }
                else if(tokens[0].equals("f")){
                    integers.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                    integers.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                    integers.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                    if(tokens.length > 4){
                        integers.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                        integers.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                        integers.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
                    }
                }
            }
            meshReader.close();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
        Mesh res = new Mesh();
        Vertex[] resV = new Vertex[vertices.size()];
        Integer[] resI = new Integer[integers.size()];
        vertices.toArray(resV);
        integers.toArray(resI);
        res.addVertices(resV, Util.toIntArray(resI));
        return res;
    }
    public static ArrayList<SceneFile> loadScene(String fileName) throws IOException{
        ZipFile zipFile = new ZipFile("./res/scenes/" + fileName);

        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        ArrayList<SceneFile> output = new ArrayList<SceneFile>();

        Mesh temp = new Mesh();

        while(entries.hasMoreElements()){
            ZipEntry entry = entries.nextElement();
            InputStream stream = zipFile.getInputStream(entry);
            File inputStreamFile = inputStreamToFile(stream);
            String[] splitArray = inputStreamFile.getName().split("\\.");
            String ext = splitArray[splitArray.length-1];
            if(ext.equals("obj")){
                temp = loadMesh(stream);
            }
            else if(ext.equals("txt")){
                BufferedReader temp2 = new BufferedReader(new FileReader(inputStreamFile));
                String baseString = temp2.readLine();
                String[] splitArray2 = baseString.split(" ");
                Util.removeEmptyStrings(splitArray2);
                float[] splitArray3 = Util.stringToFloatArray(splitArray2);
            output.add(new SceneFile(temp, splitArray3[0], splitArray3[1], splitArray3[2]));
        }
            stream.close();
            inputStreamFile.delete();

        }
        zipFile.close();
        return output;
    }
    public static File inputStreamToFile(InputStream inputStream) {

        OutputStream outputStream = null;

        File res = new File(Launch.TEMP_DIRECTORY + "\\" + String.valueOf(Time.getTime()) + ".tmp");

        try {

            // write the inputStream to a FileOutputStream
            outputStream = new FileOutputStream(res);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return res;
    }

    public static Material[] loadMtl(String fileName){
        ArrayList<Material> materialArrayList = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("./res/textures/" + fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = null;

            while((line = bufferedReader.readLine()) != null){
                String[] segments = line.split(" ");
                segments = Util.removeEmptyStrings(segments);

                if(segments.length == 0 || segments[0].equals("#")){
                    continue;
                }
                else if(segments[0].equals("newmtl")){
                    Material material = new Material(null, null);
                    while((line = bufferedReader.readLine()) != null){
                        String[] tokens = line.split(" ");
                        tokens = Util.removeEmptyStrings(tokens);


                        if(tokens.length == 0 || tokens[0].isEmpty() || tokens[0].equals("\n")){
                            break;
                        }
                        else if(tokens[0].equals("map_Kd")){
                            System.out.println(tokens[1]);
                            GL11.glEnable(GL11.GL_TEXTURE_2D);
                            System.out.println(line.replace("map_Kd ", ""));
                            material.setTexture(new Texture(TextureLoader.getTexture("jpg", new FileInputStream(new File(line.replace("map_Kd ", ""))))));
                        }

                    }
                    materialArrayList.add(material);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(Util.toMaterialArray(materialArrayList)[0] != null) {
            System.out.println("ITS NOT NULL");
        }

        return Util.toMaterialArray(materialArrayList);
    }

    /*public static CollisionMesh getCollisionMesh(String fileName){
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length-1];
        if(!ext.equals("obj")){
            System.err.println("Error: File format not supported for mesh data: " + ext);
            new Exception().printStackTrace();
            System.exit(1);
        }

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Integer> integers = new ArrayList<Integer>();
        int drawType = GL11.GL_TRIANGLES;
        BufferedReader meshReader = null;

        try{
            meshReader = new BufferedReader(new FileReader("./res/models/" + fileName));
            String line;
            while((line = meshReader.readLine()) != null){
                String[] tokens = line.split(" ");
                tokens = Util.removeEmptyStrings(tokens);

                if(tokens.length == 0 || tokens[0].equals("#")){
                    continue;
                }
                else if(tokens[0].equals("v")){
                    vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]), Float.valueOf(tokens[2]), Float.valueOf(tokens[3]))));
                }
                else if(tokens[0].equals("f")){
                    integers.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                    integers.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                    integers.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                    if(tokens.length > 4){
                        integers.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                        integers.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                        integers.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
                    }
                }
            }
            meshReader.close();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
        Vertex[] resV = new Vertex[vertices.size()];
        Integer[] resI = new Integer[integers.size()];
        vertices.toArray(resV);
        integers.toArray(resI);

        CollisionMesh returnVal = new CollisionMesh(resV, Util.toIntArray(resI));
        System.out.println(vertices);
        System.out.println(integers);
        return returnVal;
    }*/

    public static Mesh2D loadMesh2D(String fileName){
        String[] splitArray = fileName.split("\\.");
        String ext = splitArray[splitArray.length-1];

        org.newdawn.slick.opengl.Texture texture = null;
        int texID = 0;

        try{
            texture = TextureLoader.getTexture(ext, new FileInputStream(new File("./res/textures/" + fileName)));
            texID = texture.getTextureID();

        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        return new Mesh2D(texID, texture.getTextureWidth(), texture.getTextureHeight());

    }
}
