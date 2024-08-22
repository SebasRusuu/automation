import clsx from 'clsx';
import Heading from '@theme/Heading';
import styles from './styles.module.css';

type MainItem = {
    title: string;
};

type FeatureItem = {
    subtitle: string;
    Svg: React.ComponentType<React.ComponentProps<'svg'>>;
    link: string;
};

const FeatureList: FeatureItem[] = [
    {
        subtitle: 'Objectives',
        Svg: require('@site/static/img/objectives.svg').default,
        link:'/docs/Run%20project/install-project',
    },
    {
        subtitle: 'Documentation',
        Svg: require('@site/static/img/tools_programming.svg').default,
        link:'/docs/Run%20project/install-project',
    },
];

function Feature({subtitle, Svg, link}: FeatureItem) {
    const handleClick = () => {
        window.location.href = link;
    };

    return (
        <div className={clsx('col col--6')}>
            <div className="text--center">
                <Heading as="h3">{subtitle}</Heading>
                <Svg className={styles.featureSvg} role="img" onClick={handleClick} />
            </div>
        </div>
    );
}

export default function HomepageFeatures(): JSX.Element {
    return (
        <section className={styles.features}>
            <div className="container">
                <Heading as="h2" className="text--center">Clique nas imagens abaixo para saber mais</Heading>
                <div className="row">
                    {FeatureList.map((props, idx) => (
                        <Feature key={idx} {...props} />
                    ))}
                </div>
            </div>
        </section>
    );
}