import java.util.*;
import java.io.*;
import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;

class ImgRead{
  public static void main(String[] args) {
    String fn;
    String tag;
    File[] files = new File("./ref images").listFiles();
    ArrayList<MarvinImage> inputList;
    Map<String, ArrayList<MarvinImage>> tagMap = new HashMap<String, ArrayList<MarvinImage>>();
    for (File file : files) {
      fn=file.getName();
      if(fn.endsWith(".jpg") || fn.endsWith(".png")){
        tag=fn.substring(0, fn.indexOf("_"));
        if(tagMap.containsKey(tag)){
          tagMap.get(tag).add(MarvinImageIO.loadImage("./ref images/"+fn));
        }
        else{
          inputList=new ArrayList<MarvinImage>();
          inputList.add(MarvinImageIO.loadImage("./ref images/"+fn));
          tagMap.put(tag, inputList);
        }
      }
    }
    Runnable r;
  //  for(String key: tagMap.keySet()){
      r=new Run(tagMap.get("cat"));
      new Thread(r).start();
    //}
  }
}
