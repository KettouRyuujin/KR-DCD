package dcdmod.Card.Basic;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Vfx.Decade_attack;
import java.util.ArrayList;
import java.util.List;


public class Decade_Attack extends AbstractCustomCardWithType{
	
	public static final String ID = "Decade_Attack";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Decade_Attack.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_PLUS_DMG = 3;
	private List<TooltipInfo> tips;
	
	public Decade_Attack() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY,CardColorType.Decade);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = 1;
		this.tips = new ArrayList<>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("KuugaPegasusPower")||p.hasPower("RisingPegasusPower")){
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		}
		else{
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		}
		if(p.hasPower("KamenRideDecadePower")) {
			AbstractDungeon.actionManager.addToTop(new VFXAction(new Decade_attack(), 0F));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
		}
		//AbstractDungeon.actionManager.addToTop(new VFXAction(new DenO_henshin_SoundsAndAnimation(p.drawX, p.drawY), 6.0F));
		//AbstractDungeon.actionManager.addToTop(new VFXAction(new DenO_kotaewa_kiite_nai(), 2.5F));
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
		if(AbstractDungeon.player.hasPower("BladeBeatPower")) {
			this.damage += AbstractDungeon.player.getPower("BladeBeatPower").amount * 2;
			this.isDamageModified = true;
		}
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Decade_Attack();
    }
	
	@Override
    public boolean isStrike() {
        return true;
    }
	
	@Override
	public void optionDecade() {
		this.rawDescription = EXTENDED_DESCRIPTION[0];
		initializeDescription();
		setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
	}

	@Override
	public void optionKuuga() {
		if(AbstractDungeon.player.hasPower("KuugaPegasusPower")||AbstractDungeon.player.hasPower("RisingPegasusPower")){
			this.rawDescription = EXTENDED_DESCRIPTION[3];
		}
		else{
			this.rawDescription = DESCRIPTION;
		}
		initializeDescription();
	     setBackgroundTexture("img/512/attack_kuuga.png", "img/1024/attack_kuuga.png");
	}

	@Override
	public void optionAgito() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	    setBackgroundTexture("img/512/attack_agito.png", "img/1024/attack_agito.png");
	}

	@Override
	public void optionRyuki() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	    setBackgroundTexture("img/512/attack_ryuki.png", "img/1024/attack_ryuki.png");
	}

	@Override
	public void optionFaiz() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	    setBackgroundTexture("img/512/attack_faiz.png", "img/1024/attack_faiz.png");
	}

	@Override
	public void optionBlade() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	    setBackgroundTexture("img/512/attack_blade.png", "img/1024/attack_blade.png");
	}

	@Override
	public void optionHibiki() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	    setBackgroundTexture("img/512/attack_hibiki.png", "img/1024/attack_hibiki.png");
	}

	@Override
	public void optionKabuto() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	    setBackgroundTexture("img/512/attack_kabuto.png", "img/1024/attack_kabuto.png");
	}

	@Override
	public void optionDenO() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	    setBackgroundTexture("img/512/attack_deno.png", "img/1024/attack_deno.png");
	}

	@Override
	public void optionKiva() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	    setBackgroundTexture("img/512/attack_kiva.png", "img/1024/attack_kiva.png");
	}

	@Override
	public void optionNeutral() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
		setBackgroundTexture("img/512/attack_decade.png", "img/1024/attack_decade.png");
	}
	
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Decade_Attack");
        NAME = Decade_Attack.cardStrings.NAME;
        DESCRIPTION = Decade_Attack.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Decade_Attack.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.BASIC[0], DCDmod.BASIC_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.BASIC[6], DCDmod.BASIC_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.BASIC[cost], DCDmod.BASIC_P[cost]);
		}
	}

	
	
}
