package dcdmod.Card.Uncommon;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.KuugaSpecialPower;
import dcdmod.Vfx.Another_GouramAttack1;
import dcdmod.Vfx.Kuuga_FAR_Background;
import dcdmod.Vfx.Kuuga_GouramAttack1;

import java.util.ArrayList;
import java.util.List;


public class Kuuga_GouramAttack extends AbstractCustomCardWithType{
	
	public static final String ID = "Kuuga_GouramAttack";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Kuuga_GouramAttack.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 16;
	private List<TooltipInfo> tips;
	
	public Kuuga_GouramAttack() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		CardType.ATTACK, AbstractCardEnum.DCD,
        		CardRarity.UNCOMMON, CardTarget.ENEMY,CardColorType.Kuuga);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		this.damageType = DamageType.NORMAL;
		this.tips = new ArrayList<>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		CardCrawlGame.sound.playA("attackride", 0.0f);
		if(!DCDmod.AnimationTrigger){
			if(p.hasPower("KamenRideKuugaPower")){
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_GouramAttack1(p,m,this.damage)));
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_FAR_Background(false,true)));
			}
			else{
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Another_GouramAttack1(p,m,this.damage)));
			}
		}
		else {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageType), AbstractGameAction.AttackEffect.FIRE));
			if(p.hasPower("RisingMightyPower")){
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p, new KuugaSpecialPower(m,1), 1));
			}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p, new KuugaSpecialPower(m,1), 1));
			for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
				if ((!monster.isDead) && (!monster.isDying) && monster != m) {
					AbstractDungeon.actionManager.addToTop(new DamageAction(monster,new DamageInfo(p, this.damage/2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
					if(p.hasPower("RisingMightyPower")){
						AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster,p, new KuugaSpecialPower(monster,1), 1));
					}
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster,p, new KuugaSpecialPower(monster,1), 1));
				}
			}
		}
	}

	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
		if(arg0.hasPower("KuugaSpecialPower") && arg0.getPower("KuugaSpecialPower").amount >= 3) {
			this.damage += this.damage;
			this.isDamageModified = true;
		}
	}

	
	@Override
    public AbstractCard makeCopy() {
        return new Kuuga_GouramAttack();
    }
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
	public void optionDecade() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void optionKuuga() {
		this.rawDescription = EXTENDED_DESCRIPTION[0];
		initializeDescription();
	}

	@Override
	public void optionAgito() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void optionRyuki() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void optionFaiz() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void optionBlade() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void optionHibiki() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void optionKabuto() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void optionDenO() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void optionKiva() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

	@Override
	public void optionNeutral() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}
    
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Kuuga_GouramAttack");
        NAME = Kuuga_GouramAttack.cardStrings.NAME;
        DESCRIPTION = Kuuga_GouramAttack.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Kuuga_GouramAttack.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.UNCOMMON[0], DCDmod.UNCOMMON_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.UNCOMMON[6], DCDmod.UNCOMMON_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.UNCOMMON[cost], DCDmod.UNCOMMON_P[cost]);
		}
	}

	
	
}
