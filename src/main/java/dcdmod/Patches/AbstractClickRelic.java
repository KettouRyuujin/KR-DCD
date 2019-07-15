package dcdmod.Patches;

import basemod.abstracts.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.helpers.input.*;
import org.apache.logging.log4j.*;

public abstract class AbstractClickRelic extends CustomRelic
{
    public static final Logger logger;
    private boolean RclickStart;
    private boolean Rclick;
    
    public AbstractClickRelic(final String id, final Texture texture, final AbstractRelic.RelicTier tier, final AbstractRelic.LandingSound sfx) {
        super(id, texture, tier, sfx);
        this.Rclick = false;
        this.RclickStart = false;
    }
    
    protected abstract void onRightClick();
    
    public void update() {
        super.update();
        if (this.RclickStart && InputHelper.justReleasedClickRight) {
            if (this.hb.hovered) {
                this.Rclick = true;
            }
            this.RclickStart = false;
        }
        if (this.isObtained && this.hb != null && this.hb.hovered && InputHelper.justClickedRight) {
            this.RclickStart = true;
        }
        if (this.Rclick) {
            this.Rclick = false;
            this.onRightClick();
        }
    }
    
    static {
        logger = LogManager.getLogger(AbstractClickRelic.class);
    }
}
