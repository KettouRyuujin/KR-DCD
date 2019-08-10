package dcdmod.Card.Common;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcdmod.DCDmod;
import dcdmod.Actions.RemoveFormRideAction;
import dcdmod.Actions.RemoveHalfAttributeAction;
import dcdmod.Card.Special.PegasusDefend;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.KuugaPegasusPower;
import dcdmod.Power.RisingPegasusPower;
import dcdmod.Vfx.Kuuga_PegasusSoundsAndAnimation;



public class FormRidePegasus extends AbstractCustomCardWithType{
	
	public static final String ID = "FormRidePegasus";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/FormRidePegasus.png";
	private static final int COST = 2;

	
	
	public FormRidePegasus() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF,CardColorType.Kuuga);
		
		this.baseMagicNumber = this.magicNumber = 5;
		this.tags.add(DCDmod.RiderCard);
		this.tags.add(DCDmod.KamenRide);
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		CardCrawlGame.sound.playA("formride", 0.0f);
		if((p.hasPower("RisingMightyPower")||p.hasPower("RisingDragonPower")||p.hasPower("RisingTitanPower"))&&!p.hasPower("KuugaPegasusPower")&&!p.hasPower("RisingPegasusPower")) {
			AbstractDungeon.actionManager.addToTop(new RemoveHalfAttributeAction(p, p));
			AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(p, p));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RisingPegasusPower(p,3),3));
			if(DCDmod.AnimationTrigger){
				final Decade Decade = (Decade)p;
				Decade.Trickster(8);
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_PegasusSoundsAndAnimation(), 0.5F));
			}
			else{
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_PegasusSoundsAndAnimation(), 1.75F));
			}
		}
		else if(!p.hasPower("KuugaPegasusPower")&&!p.hasPower("RisingPegasusPower")) {
			AbstractDungeon.actionManager.addToTop(new RemoveHalfAttributeAction(p, p));
			AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(p, p));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KuugaPegasusPower(p,1),1));
			if(DCDmod.AnimationTrigger){
				final Decade Decade = (Decade)p;
				Decade.Trickster(8);
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_PegasusSoundsAndAnimation(), 0.5F));
			}
			else{
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_PegasusSoundsAndAnimation(), 1.75F));
			}
		}
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new PegasusDefend(), 1, true, true));
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PegasusDefend(), 1));
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(!p.hasPower("KamenRideKuugaPower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		return canUse;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new FormRidePegasus();
    }
	
	
	@Override
	public void optionDecade() {
		
	}

	@Override
	public void optionKuuga() {
		if(AbstractDungeon.player.hasPower("RisingMightyPower")||AbstractDungeon.player.hasPower("RisingDragonPower")||AbstractDungeon.player.hasPower("RisingTitanPower")) {
			this.rawDescription = EXTENDED_DESCRIPTION[1];
		}
		initializeDescription();
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
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("FormRidePegasus");
        NAME = FormRidePegasus.cardStrings.NAME;
        DESCRIPTION = FormRidePegasus.cardStrings.DESCRIPTION;        
        EXTENDED_DESCRIPTION = FormRidePegasus.cardStrings.EXTENDED_DESCRIPTION;
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
