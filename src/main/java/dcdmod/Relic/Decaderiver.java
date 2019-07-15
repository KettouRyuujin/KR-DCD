package dcdmod.Relic;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import dcdmod.DCDmod;
//import dcdmod.Actions.ApotheosisAction;
import dcdmod.Actions.RemoveKamenRideAction;
import dcdmod.Helper.SpecialRideBooker;
import dcdmod.Patches.AbstractClickRelic;
import dcdmod.Power.KamenRideDecadePower;
import dcdmod.Vfx.Decade_hensin;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;



 
public class Decaderiver extends AbstractClickRelic{
  public static final String ID = "Decadriver";
  boolean ishensin = false;
  boolean ismonsterroom = false;

  
  public Decaderiver()
  
   {super(ID, new Texture(Gdx.files.internal("img/relics/Decadriver.png")), RelicTier.STARTER, LandingSound.MAGICAL);}
  
  @Override
  public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
 } 

  @Override
  public void atBattleStart() {
	  ismonsterroom = true;
   }
  
   
   
   @Override
   public void atTurnStart() {
	   SpecialRideBooker.decadenextturn = true;
	   ishensin = false;
   }
   
   @Override
   public void onUseCard(AbstractCard card, UseCardAction action) {
	   if(card.hasTag(DCDmod.FormRide)||card.hasTag(DCDmod.KamenRide)) {
		   flash();
			AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
			ArrayList<AbstractCard> groupCopy = new ArrayList<AbstractCard>();
			Iterator<?> var4 = AbstractDungeon.player.hand.group.iterator();

			while (true) {
				while (var4.hasNext()) {
					AbstractCard c = (AbstractCard) var4.next();
					if (c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce) {
						groupCopy.add(c);
					} else {
						logger.info("COST IS 0: " + c.name);
					}
				}

				var4 = AbstractDungeon.actionManager.cardQueue.iterator();

				while (var4.hasNext()) {
					CardQueueItem i = (CardQueueItem) var4.next();
					if (i.card != null) {
						logger.info("INVALID: " + i.card.name);
						groupCopy.remove(i.card);
					}
				}

				AbstractCard c = null;
				if (groupCopy.isEmpty()) {
					logger.info("NO VALID CARDS");
				} else {
					logger.info("VALID CARDS: ");
					Iterator<AbstractCard> var9 = groupCopy.iterator();

					while (var9.hasNext()) {
						AbstractCard cc = (AbstractCard) var9.next();
						logger.info(cc.name);
					}

					c = (AbstractCard) groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
				}

				if (c != null) {
					logger.info("Decaderiver: " + c.name);
					c.setCostForTurn(0);
				} else {
					logger.info("ERROR: Decaderiver NOT WORKING");
				}
				break;
			}
	   }
   }

   @Override
   protected void onRightClick() {
	   //AbstractDungeon.actionManager.addToBottom(new ApotheosisAction());
	   if(AbstractDungeon.player.hasPower("KamenRideDecadePower")) {
		   AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "已进入Decade状态", true));
	   }
	   else if(ishensin){
		   AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "本回合已使用过驱动器", true));
	   }
	   else if(!ismonsterroom){
		   AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "没必要在这里变身", true));
	   }
	   else if(SpecialRideBooker.nodecade) {
		   flash();
		   CardCrawlGame.sound.playA("driversounds", 0.0f);
		   CardCrawlGame.sound.playA("test1", 0.0f);//马赛克声音
		   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KamenRideDecadePower(AbstractDungeon.player,1),1));
		   AbstractDungeon.actionManager.addToBottom(new RemoveKamenRideAction(AbstractDungeon.player, AbstractDungeon.player));
		   ishensin = true;
	   }
	   else{
		   flash();
		   CardCrawlGame.sound.playA("BGM", 0.0f);
		   CardCrawlGame.sound.playA("people_hensin", 0.0f);
		   CardCrawlGame.sound.playA("decade_hensin", 0.0f);
		   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new KamenRideDecadePower(AbstractDungeon.player,1),1));
		   AbstractDungeon.actionManager.addToBottom(new VFXAction(new Decade_hensin(AbstractDungeon.player.drawX - 200.00f, AbstractDungeon.player.drawY + 250.00f), 7F));
		   ishensin = true;
	   }
   }  
   
   public void onVictory() {
	   SpecialRideBooker.decadenextturn = true;
	   ishensin = false;
	   ismonsterroom = false;
   }
   

   
}
 