package com.othelle.core.tree;

import com.othelle.core.tree.impl.DummyTreeItem;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

/**
 * User: v.vlasov
 * Date: 12/9/11
 */
public class TreeUtilTest {
    @Test
    public void testFindTheSameLevel() throws Exception {
        TreeItem level1 = new DummyTreeItem("level1");
        TreeItem level21 = new DummyTreeItem("level2");
        TreeItem level31 = new DummyTreeItem("level2");
        TreeItem level32 = new DummyTreeItem("level2");
        TreeItem level22 = new DummyTreeItem("level2");
        TreeItem level33 = new DummyTreeItem("level2");
        TreeItem level34 = new DummyTreeItem("level2");

        level21.setParent(level1);
        level31.setParent(level21);
        level32.setParent(level21);
        level22.setParent(level1);
        level33.setParent(level22);
        level34.setParent(level22);

        List<TreeItem> items = Arrays.asList(level1, level21, level31, level32, level22, level33, level34);

        assertThat(TreeUtil.findTheSameLevel(items, 4), Matchers.equalTo(level21));
        assertThat(TreeUtil.findTheSameLevel(items, 0), Matchers.nullValue());
        assertThat(TreeUtil.findTheSameLevel(items, 2), Matchers.nullValue());
        assertThat(TreeUtil.findTheSameLevel(items, 3), Matchers.equalTo(level31));
        assertThat(TreeUtil.findTheSameLevel(items, 5), Matchers.nullValue());
        assertThat(TreeUtil.findTheSameLevel(items, 6), Matchers.equalTo(level33));
    }
}
