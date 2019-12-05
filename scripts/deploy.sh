oc new-project eventbus
oc project eventbus
oc policy add-role-to-user admin efraimla -n $(oc project -q)
oc policy add-role-to-user view -n $(oc project -q) -z default
oc policy add-role-to-user view system:serviceaccount:$(oc project -q):default -n $(oc project -q)

./mvnw clean fabric8:deploy
