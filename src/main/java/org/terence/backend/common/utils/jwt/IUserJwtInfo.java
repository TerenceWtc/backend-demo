package org.terence.backend.common.utils.jwt;

/**
 * @author terence
 * @since 2019/2/19 9:29
 */
public interface IUserJwtInfo {

    /** get user id
     * @return String
     */
    String getId();

    /** get user name
     * @return String
     */
    String getUsername();

    /** get real name
     * @return String
     */
    String getName();
}
