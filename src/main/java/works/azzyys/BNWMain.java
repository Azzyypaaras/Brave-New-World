package works.azzyys;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BNWMain implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("bnw");

	@Override
	public void onInitialize() {
		LOGGER.info("Bon voyage old world!");
	}
}