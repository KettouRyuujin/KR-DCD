package dcdmod.Relic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import basemod.abstracts.CustomRelic;

public class PhotonAccelerationRelic extends CustomRelic{
	 public static final String ID = "PhotonAccelerationRelic";

	  
	  public PhotonAccelerationRelic()
	  
	   {
	     super(ID, new Texture(Gdx.files.internal("img/1024/orb-dark.png")),new Texture(Gdx.files.internal("img/1024/orb-dark.png")), RelicTier.STARTER, LandingSound.MAGICAL);
	           }
	  
		public void onExhaust(final AbstractCard card) {
			/*PhotonAccelerationPower.x+=1;
			if(PhotonAccelerationPower.x >= 5) {
				   AbstractCard c = new Kabuto_ClockUp();
				   AbstractDungeon.player.hand.moveToDeck(c, false);
			}*/
		}
		
		public void onManualDiscard() {
			/*PhotonAccelerationPower.x+=1;
			if(PhotonAccelerationPower.x >= 5) {
				   AbstractCard c = new Kabuto_ClockUp();
				   AbstractDungeon.player.hand.moveToDeck(c, false);
			}*/
		}
		
	  
	  @Override
	  public String getUpdatedDescription() {
	     return this.DESCRIPTIONS[0];
	 } 
}

