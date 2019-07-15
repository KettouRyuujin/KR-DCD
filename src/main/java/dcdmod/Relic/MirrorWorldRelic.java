package dcdmod.Relic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomRelic;
import dcdmod.Power.DragrederPower;

public class MirrorWorldRelic extends CustomRelic{
	 public static final String ID = "MirrorWorldRelic";

	  
	  public MirrorWorldRelic()
	  
	   {
	     super(ID, new Texture(Gdx.files.internal("img/1024/orb-dark.png")),new Texture(Gdx.files.internal("img/1024/orb-dark.png")), RelicTier.STARTER, LandingSound.MAGICAL);
	           }
	  
		public void onMonsterDeath(final AbstractMonster m) {
			if (m.currentHealth == 0 /*&& !AbstractDungeon.getMonsters().areMonstersBasicallyDead()*/) {
				AbstractDungeon.player.heal(((AbstractDungeon.player.maxHealth*10)/100));
				if(AbstractDungeon.player.hasPower("DragrederPower")) {
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DragrederPower(AbstractDungeon.player, 1), 1));
				}
			}
		}
	  
	  @Override
	  public String getUpdatedDescription() {
	     return this.DESCRIPTIONS[0];
	 } 
}

