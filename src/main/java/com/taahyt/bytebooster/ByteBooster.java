package com.taahyt.bytebooster;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BBConstants.MODID)
public class ByteBooster
{
    private static final Logger logger = LogManager.getLogger();
    public ByteBooster()
    {
    }

    public static Logger getLogger() {
        return logger;
    }
}
