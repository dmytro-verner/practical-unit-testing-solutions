package chapter_3.exercise_3_11_3;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    private Map<String, String> map = new HashMap<String, String>();

    @Test
    public void putObjectRetrievedWithGet(){
        map.put("1", "1");

        Assert.assertTrue("Added object with put() is retrieved with get()", "1".equals(map.get("1")));
    }

    @Test
    public void addingSecondObjectWithSameKeyResultsInOldReplaced(){
        map.put("1", "1");
        map.put("1", "2");

        Assert.assertEquals("Adding object with the same key results in the old one being replaced",
                "2", map.get("1"));
    }

    @Test
    public void clearRemovesAllTheContent(){
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        map.clear();

        Assert.assertTrue(map.isEmpty());
    }

    @Test
    public void nullCanBeUsedAsKey(){
        map.put(null, "1");

        Assert.assertEquals("1", map.get(null));
    }
}
