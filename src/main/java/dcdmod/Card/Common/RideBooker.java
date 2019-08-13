package dcdmod.Card.Common;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.SingingBowl;

import dcdmod.DCDmod;
import dcdmod.Card.SelectCard.GunForm;
import dcdmod.Card.SelectCard.SwordForm;
import dcdmod.Card.Special.Decade_Blast;
import dcdmod.Card.Special.RideBooker_Attack;
import dcdmod.Card.Special.RideBooker_Shoot;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.GunFormPower;
import dcdmod.Power.SwordFormPower;



public class RideBooker extends AbstractCustomCardWithType{
	
	public static final String ID = "RideBooker";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/RideBooker.png";
	private static final int COST = 1;
	
	
	
	public RideBooker() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		boolean remove = false;
		for(AbstractRelic r: AbstractDungeon.player.relics) {
			if(r.relicId.equals("Singing Bowl")) {
				AbstractDungeon.player.relics.remove(r);
				
				remove = true;
				break;
			}
		}
		ArrayList<AbstractCard> temp = new ArrayList<>();
		AbstractCard c = new GunForm();
		temp.add(c);
		AbstractCard c1 = new SwordForm();
		temp.add(c1);
		AbstractDungeon.cardRewardScreen.open(temp, AbstractDungeon.cardRewardScreen.rItem, "选择1种模式");
		if(remove) {
			AbstractRelic r = new SingingBowl();
			AbstractDungeon.player.relics.add(r);
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new RideBooker();
    }
	
	
	@Override
	public void optionDecade() {

	}

	@Override
	public void optionKuuga() {

	}

	@Override
	public void optionAgito() {

	}

	@Override
	public void optionRyuki() {

	}

	@Override
	public void optionFaiz() {

	}

	@Override
	public void optionBlade() {

	}

	@Override
	public void optionHibiki() {

	}

	@Override
	public void optionKabuto() {

	}

	@Override
	public void optionDenO() {

	}

	@Override
	public void optionKiva() {

	}

	@Override
	public void optionNeutral() {

	}
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeBaseCost(0);
		}
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("RideBooker");
        NAME = RideBooker.cardStrings.NAME;
        DESCRIPTION = RideBooker.cardStrings.DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(AbstractDungeon.player != null) {
			if(AbstractDungeon.cardRewardScreen.onCardSelect) {
				for(AbstractCard c3 : AbstractDungeon.player.masterDeck.group) {
					if(c3.cardID.equals("SwordForm")) {
						if(!AbstractDungeon.player.hasPower("SwordFormPower")) {
							AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new RideBooker_Attack(), 3, true, true));
							AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SwordFormPower(AbstractDungeon.player, 1), 1));
							AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
						}
						if (AbstractDungeon.player.hasPower("GunFormPower")){
							AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "GunFormPower"));
						}
						AbstractDungeon.player.masterDeck.removeCard(c3);
						break;
					}
					else if(c3.cardID.equals("GunForm")) {
						if(!AbstractDungeon.player.hasPower("GunFormPower")) {
							AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new RideBooker_Shoot(), 3, true, true));
							AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Decade_Blast(), 1, true, true));
							AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new GunFormPower(AbstractDungeon.player, 1), 1));
						}
						if (AbstractDungeon.player.hasPower("SwordFormPower")){
							AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "SwordFormPower"));
						}
						AbstractDungeon.player.masterDeck.removeCard(c3);
						break;
					}
				}
			}
		}
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.COMMON[0], DCDmod.COMMON_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.COMMON[6], DCDmod.COMMON_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.COMMON[cost], DCDmod.COMMON_P[cost]);
		}
	}

	
	
}
