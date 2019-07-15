package dcdmod.Card.Status;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import dcdmod.Actions.PhotonAction;
import dcdmod.Card.Rare.Kabuto_ClockUp;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Power.KabutoMaskedPower;
import dcdmod.Power.PhotonAccelerationPower;





public class Kabuto_Photon extends CustomCard{
	
	public static final String ID = "Kabuto_Photon";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Kabuto_Photon.png";
	private static final int COST = 0;
	
	public Kabuto_Photon() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.STATUS, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new PhotonAction(this));
	}
	
	@Override
	public void triggerOnExhaust() {
		if(AbstractDungeon.player.hasPower("PhotonAccelerationPower")) {
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
			PhotonAccelerationPower.x+=1;
			if(PhotonAccelerationPower.x >= 5) {
				   AbstractCard c = new Kabuto_ClockUp();
				   AbstractDungeon.player.hand.moveToDeck(c, false);
			}
		}
	}
	
	@Override
	public void triggerOnManualDiscard() {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player,new DamageInfo(AbstractDungeon.player, 1, DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		if(AbstractDungeon.player.hasPower("KabutoMaskedPower")){
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
			KabutoMaskedPower.PhotonPoint += 1;
		}
		if(AbstractDungeon.player.hasPower("PhotonAccelerationPower")) {
			PhotonAccelerationPower.x+=1;
			if(PhotonAccelerationPower.x >= 5) {
				   AbstractCard c = new Kabuto_ClockUp();
				   AbstractDungeon.player.hand.moveToDeck(c, false);
			}
		}
	}
	
	@Override
	public void triggerWhenDrawn() {
		if(AbstractDungeon.player.hasPower("PhotonAccelerationPower")) {
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Kabuto_Photon();
    }
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		return true;
	}
	
    
    public void upgrade() {
        if (!this.upgraded) {

        }
	
    }
	
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Kabuto_Photon");
        NAME = Kabuto_Photon.cardStrings.NAME;
        DESCRIPTION = Kabuto_Photon.cardStrings.DESCRIPTION;
    }
    
}
