package Backend.Data;

import org.apache.commons.lang3.RandomStringUtils;

public class ConstantData {

    public static final String
            userName = RandomStringUtils.random(10, true, false),

            password = "Sopoopo13!",

            generateTokenApiUserAndPasswordRequiredMessage = "UserName and Password required.",

            generateTokenResponseCodeMessage = "1200";
}
