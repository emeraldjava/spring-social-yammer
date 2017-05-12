package org.springframework.social.yammer.api.util;

/**
 * Created by pauloconnell on 12/05/17.
 */
public class PaginationUtil {

    /**
     * Return the total number of page calls to make for a group with totalSize.
     * @param totalSize
     * @param pageSize
     * @return
     */
    public int calculatePageCount(int totalSize,int pageSize) {

        if(pageSize>=totalSize) {
            return 1;
        } else {
            int totalPages = totalSize / pageSize;
            if (totalSize % pageSize > 0)
                totalPages++;
            return totalPages;
        }
    }
}
