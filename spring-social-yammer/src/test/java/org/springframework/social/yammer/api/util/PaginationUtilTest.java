package org.springframework.social.yammer.api.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pauloconnell on 12/05/17.
 */
public class PaginationUtilTest {

    private PaginationUtil paginationUtil;

    @Before
    public void setup() {
        this.paginationUtil = new PaginationUtil();
    }

    @Test
    public void testCalculatePageCount() throws Exception {
        assertEquals("1",1,this.paginationUtil.calculatePageCount(42,50));
    }

    @Test
    public void testCalculatePageCount0() throws Exception {
        assertEquals("0",1,this.paginationUtil.calculatePageCount(0,50));
    }

    @Test
    public void testCalculatePageCount50() throws Exception {
        assertEquals("50",1,this.paginationUtil.calculatePageCount(50,50));
    }

    @Test
    public void testCalculatePageCount72() throws Exception {
        assertEquals("72",2,this.paginationUtil.calculatePageCount(72,50));
    }

    @Test
    public void testCalculatePageCount100() throws Exception {
        assertEquals("100",2,this.paginationUtil.calculatePageCount(100,50));
    }

    @Test
    public void testCalculatePageCount149() throws Exception {
        assertEquals("149",3,this.paginationUtil.calculatePageCount(149,50));
    }

    @Test
    public void testCalculatePageCount150() throws Exception {
        assertEquals("150",3,this.paginationUtil.calculatePageCount(150,50));
    }

    @Test
    public void testCalculatePageCount151() throws Exception {
        assertEquals("151",4,this.paginationUtil.calculatePageCount(151,50));
    }
}