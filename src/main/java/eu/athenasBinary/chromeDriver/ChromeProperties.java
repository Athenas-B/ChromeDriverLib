package eu.athenasBinary.chromeDriver;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChromeProperties {
    private boolean sandbox;
    private boolean headless;
    private boolean incognito;
    private boolean showChrome;
    private long driverTimeoutSeconds = 10;
}