oc new-project eventbus
oc policy add-role-to-user admin efraimla -n eventbus
oc policy add-role-to-user view -n eventbus -z default
oc policy add-role-to-user view system:serviceaccount:$(oc project -q):default -n $(oc project -q)

mvn clean fabric8:deploy
