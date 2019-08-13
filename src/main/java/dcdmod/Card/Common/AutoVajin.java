package dcdmod.Card.Common;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.DexterityPower;

import dcdmod.DCDmod;
import dcdmod.Actions.EnterButtonAction;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.AutoVajinPower;
import dcdmod.Vfx.AutoVajin_appear;
import dcdmod.Vfx.AutoVajin_sounds;


public class AutoVajin extends AbstractCustomCardWithType{
	
	public static final String ID = "AutoVajin";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/AutoVajin.png";
	private static final int COST = 2;

	
	
	public AutoVajin() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF,CardColorType.Faiz);
		this.tags.add(DCDmod.RiderCard);
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		CardCrawlGame.sound.playA("attackride", 0.0f);
		AbstractDungeon.actionManager.addToTop(new VFXAction(new AutoVajin_sounds(), 0.0F));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new AutoVajin_appear(), 2.5F));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AutoVajinPower(p, 3), 3));
		if(EnterButtonAction.FaizEdge&&p.hasOrb()) {
			for(AbstractOrb o : p.orbs) {
				if(o.ID == "FaizPointer") {
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 2), 2));
					break;
				}
			}
		}
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(p.hasPower("AutoVajinPower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		return canUse;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new AutoVajin();
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
			this.upgradeBaseCost(1);
		}
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("AutoVajin");
        NAME = AutoVajin.cardStrings.NAME;
        DESCRIPTION = AutoVajin.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = AutoVajin.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
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
